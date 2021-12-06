package ca.mcgill.ecse321.library_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.library_android.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.Header;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String error = null;
    private String currentUser = null;
    private ArrayAdapter<String> reservedItemAdapter;
    private List<String> reservedItemTitles = new ArrayList<>();
    private String currentTimeSlot;
    private String currentItemId;
    private String itemTypeSelected_Reservation = null;
    private boolean UserInfoOn = false;
    private Long userId = null;
    private String password = null;

//  cancel reservation and events initializations
    private String userType = null;
    private String selectedReservationId = null;
    private String selectedEventId = null;
    private String selectedUserId = null;

    private void setCurrentUser(String username) {
        this.currentUser = username;
    }

    private String getCurrentUser() {
        return this.currentUser;
    }

    private void refreshErrorMessage() {
        // set the error message
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setContentView(R.layout.login_page);
        refreshErrorMessage();
    }

    public void refreshInitialReservationList(View v) {
        final ArrayAdapter<String> adapter = reservedItemAdapter;
        final List<String> titles = reservedItemTitles;
        titles.add("Please select...");
        adapter.notifyDataSetChanged();
    }

    public void refreshReservationList(View v) {
        final ArrayAdapter<String> adapter = reservedItemAdapter;
        final List<String> titles = reservedItemTitles;
        HttpUtils.get("onlineuser/reservations/username/" + currentUser, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                titles.clear();
                titles.add("Please select...");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        titles.add(response.getJSONObject(i).getString("reservationId"));
                    } catch (Exception e) {
                        error = e.getMessage();
                    }
                    refreshErrorMessage();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void deleteUserConfirm(View v) {
        error = "";

        System.out.println("Current User" + currentUser);

        HttpUtils.delete("onlineuser/delete/username/" + currentUser, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                error = "";
                currentUser = null;

                System.out.println("Status 1: " + statusCode);
                toLogin(v);
            }




            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                System.out.println("Failure 1: " + responseString);
//                Toast.makeText(getApplicationContext(),
//                        R.string.deleteAccount_request_failure, Toast.LENGTH_SHORT).show();

                System.out.println("Status: " + statusCode);
                error = "";
                currentUser = null;
                toLogin(v);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                System.out.println("Error JSON: " + errorResponse);
                System.out.println("Status 2: " + statusCode);

                Toast.makeText(getApplicationContext(),
                        R.string.deleteAccount_request_failure, Toast.LENGTH_SHORT).show();
                toOnlineUser(v);
            }
        });
    }

    public void deleteUserPrompt(View v) {
        setContentView(R.layout.delete_online_user);
    }

    public void deleteUserReject(View v) {
        toOnlineUser(v);
    }

    public String checkUserType() {

        HttpUtils.get("offlineuser/userId/" + userId, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                userType = "offline";
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                HttpUtils.get("onlineuser/userId/" + userId, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        userType = "online";
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        System.out.println("find online user by id error: " + errorResponse);
                    }

                });
            }


        });

        return userType;
    }

    public void cancelReservationConfirm(View v) {
        checkUserType();

        if(userType != "online" || userType != "offline") {
            return;
        }

        String path = userType == "online" ? "onlineuser": "offlineuser";
        String fullPath = path + "/cancelreservation/userId/" + selectedUserId + "?reservationId=" + selectedReservationId;

        HttpUtils.post(fullPath, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext()
                        ,"Reservation has been cancelled"
                        , Toast.LENGTH_SHORT).show();
                toOnlineUser(v);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Toast.makeText(getApplicationContext()
                        ,"Reservation not cancelled. Please try again"
                        , Toast.LENGTH_SHORT).show();
                toOnlineUser(v);
            }

        });

    }

    public void cancelReservationReject(View v) {
        toOnlineUser(v);
    }

    public void cancelEventConfirm(View v) {
        checkUserType();

        if(userType != "online" || userType != "offline") {
            return;
        }

        String path = userType == "online" ? "onlineuser": "offlineuser";

        String fullPath = path + "/cancelevent/userId/" + selectedUserId + "?eventId=" + selectedReservationId;

        HttpUtils.post(fullPath, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext()
                        , "Event has been cancelled"
                        , Toast.LENGTH_SHORT).show();
                toOnlineUser(v);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Toast.makeText(getApplicationContext()
                        ,"Event not cancelled. Please try again"
                        , Toast.LENGTH_SHORT).show();
                toOnlineUser(v);
            }

        });
    }

    public void cancelEventReject(View v) {
        toOnlineUser(v);
    }

    public void loginUser(View v) {
        error = "";
        final TextView username = (TextView) findViewById(R.id.login_username);
        final TextView password = (TextView) findViewById(R.id.login_password);
        HttpUtils.post("onlineuser/login?username=" + username.getText().toString() + "&password=" + password.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                setCurrentUser(username.getText().toString());
                setCurrentUser(username.getText().toString());
                refreshErrorMessage();
                username.setText("");
                password.setText("");
                toOnlineUser(v);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });
    }

    public void changePassword(View v) {
        error = "";
        TextView curError = (TextView) findViewById(R.id.changePassword_error);
        curError.setText("");
        TextView message = (TextView) findViewById(R.id.changePassword_message);
        message.setText("");
        final TextView username = (TextView) findViewById(R.id.changePassword_userName);
        final TextView oldPassword = (TextView) findViewById(R.id.changePassword_oldPassword);
        final TextView newPassword = (TextView) findViewById(R.id.changePassword_newPassword);
        final TextView confirmPassword = (TextView) findViewById(R.id.changePassword_confirm_newPassword);
        if(!newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
            curError.setText("Two Entered New Password Does Not Match.");
            return;
        }
        HttpUtils.put("/onlineuser/update/password?username=" + username.getText().toString() + "&password=" + oldPassword.getText().toString() + "&newPassword=" + newPassword.getText().toString(), new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                message.setText("Password Changed Successfully!");
                username.setText("");
                oldPassword.setText("");
                newPassword.setText("");
                confirmPassword.setText("");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                curError.setText(error);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                curError.setText(error);
            }
        });
    }

    public void updateUserInfo(View v) {
        error = "";
        TextView curError = (TextView) findViewById(R.id.changeUserInfo_error);
        curError.setText("");
        TextView message = (TextView) findViewById(R.id.changeUserInfo_message);
        message.setText("");
        TextView username = (TextView)findViewById(R.id.changeUserInfo_username);
        TextView email = (TextView)findViewById(R.id.changeUserInfo_email);
        TextView firstName = (TextView)findViewById(R.id.changeUserInfo_firstName);
        TextView lastName = (TextView)findViewById(R.id.changeUserInfo_lastName);
        TextView address = (TextView)findViewById(R.id.changeUserInfo_address);
        CheckBox isLocal = (CheckBox) findViewById(R.id.changeUserInfo_local);
        TextView enteredPassword = (TextView)findViewById(R.id.changeUserInfo_password);

        if(!enteredPassword.getText().toString().equals(password)) {
            curError.setText("Wrong Password Entered.");
            return;
        }

        HttpUtils.put("/onlineuser/update?userId=" + userId + "&firstName=" + firstName.getText().toString() + "&lastName=" + lastName.getText().toString() + "&address=" + address.getText().toString() + "&isLocal=" + isLocal.isChecked() + "&username=" + username.getText().toString() + "&password=" + enteredPassword.getText().toString() + "&email=" + email.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                message.setText("User Information Changed Successfully!");
                currentUser = username.getText().toString();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                curError.setText(error);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                curError.setText(error);
            }
        });
    }

    public void Logout(View v) {
        error = "";
        currentUser = null;
        toLogin(v);
    }

    public void showPersonalInfo(View v) {
        error = "";
        setContentView(R.layout.manageaccount_page);
        TextView curError = (TextView)findViewById(R.id.manageaccount_error);
        curError.setText("");
        TextView username = (TextView)findViewById(R.id.accountManagement_username_text);
        TextView email = (TextView)findViewById(R.id.accountManagement_email_text);
        TextView firstName = (TextView)findViewById(R.id.accountManagement_firstName_text);
        TextView lastName = (TextView)findViewById(R.id.accountManagement_lastName_text);
        TextView address = (TextView)findViewById(R.id.accountManagement_address_text);
        TextView isLocal = (TextView)findViewById(R.id.accountManagement_isLocal_text);
        TextView userId = (TextView)findViewById(R.id.accountManagement_userId_text);
        if(UserInfoOn == true) {
            UserInfoOn = false;
            return;
        }
        HttpUtils.get("onlineuser/username/" + currentUser,new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                try {
                    username.setText("Username: " + response.getString("username"));
                    email.setText("Email: " + response.getString("email"));
                    firstName.setText("FirstName: " + response.getString("firstName"));
                    lastName.setText("LastName: " + response.getString("lastName"));
                    address.setText("Address: " + response.getString("address"));
                    isLocal.setText("IsLocal: " + response.getString("isLocal"));
                    userId.setText("UserId: " + response.getString("userId"));
                    UserInfoOn = true;
                } catch (JSONException e) {
                    String newError = e.getMessage();
                    curError.setText(newError);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                curError.setText(error);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                curError.setText(error);
            }
        });
    }

    public void toManageAccount(View v) {
        setContentView(R.layout.manageaccount_page);
        UserInfoOn = false;
        userId = null;
        password = null;
    }

    public void toChangePassword(View v) {
        setContentView(R.layout.changepassword_page);
    }

    public void toChangeUserInfo(View v) {
        setContentView(R.layout.changeuserinfo_page);
        error = "";
        TextView curError = (TextView) findViewById(R.id.changeUserInfo_error);
        curError.setText("");
        TextView message = (TextView) findViewById(R.id.changeUserInfo_message);
        message.setText("");
        TextView username = (TextView)findViewById(R.id.changeUserInfo_username);
        TextView email = (TextView)findViewById(R.id.changeUserInfo_email);
        TextView firstName = (TextView)findViewById(R.id.changeUserInfo_firstName);
        TextView lastName = (TextView)findViewById(R.id.changeUserInfo_lastName);
        TextView address = (TextView)findViewById(R.id.changeUserInfo_address);
        CheckBox isLocal = (CheckBox) findViewById(R.id.changeUserInfo_local);

        HttpUtils.get("onlineuser/username/" + currentUser,new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                try {
                    username.setText(response.getString("username"));
                    email.setText(response.getString("email"));
                    firstName.setText(response.getString("firstName"));
                    lastName.setText(response.getString("lastName"));
                    address.setText(response.getString("address"));
                    isLocal.setChecked(response.getBoolean("isLocal"));
                    userId = response.getLong("userId");
                    password = response.getString("password");
                } catch (JSONException e) {
                    String newError = e.getMessage();
                    curError.setText(newError);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                curError.setText(error);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                curError.setText(error);
            }
        });
    }

    public void toSignUp(View v) {
        setContentView(R.layout.signup_page);
    }

    public void toLogin(View v) {
        setContentView(R.layout.login_page);
    }

    public void toOnlineUser(View v) {
        setContentView(R.layout.onlineuser_page);
    }

    public void signUp(View v) {
        error = "";
        final TextView firstName = (TextView) findViewById(R.id.signup_firstname);
        final TextView lastName = (TextView) findViewById(R.id.signup_lastname);
        final TextView address = (TextView) findViewById(R.id.signup_address);
        final CheckBox isLocal = (CheckBox) findViewById(R.id.signup_local);
        final TextView username = (TextView) findViewById(R.id.signup_username);
        final TextView password = (TextView) findViewById(R.id.signup_password);
        final TextView email = (TextView) findViewById(R.id.signup_email);
        HttpUtils.post("onlineuser/create?firstName=" + firstName.getText().toString() + "&lastName=" + lastName.getText().toString() + "&address=" + address.getText().toString() + "&isLocal=" + isLocal.isChecked() + "&username=" + username.getText().toString() + "&password=" + password.getText().toString() + "&email=" + email.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                setCurrentUser(username.getText().toString());
                refreshErrorMessage();
                firstName.setText("");
                lastName.setText("");
                address.setText("");
                isLocal.setChecked(false);
                username.setText("");
                password.setText("");
                email.setText("");
                toOnlineUser(v);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });
    }

    public void toReservation(View v) {
        setContentView(R.layout.reservation_page);
    }

    public void toViewReservation(View v) {
        setContentView(R.layout.view_reservation_page);
        Spinner reservedItemSpinner = (Spinner) findViewById(R.id.reservationSpinner);
        reservedItemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reservedItemTitles);
        reservedItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reservedItemSpinner.setAdapter(reservedItemAdapter);
        refreshInitialReservationList(this.getCurrentFocus());
    }

    public void reloadReservationPage(View v) {
        final TextView reservationConfirm = (TextView) findViewById(R.id.reservation_returnDateConfirm_text);
        final TextView timeSlotConfirmed = (TextView) findViewById(R.id.reservation_Confirm_text);

        reservationConfirm.setText("");
        reservationConfirm.setVisibility(View.GONE);
        timeSlotConfirmed.setText("");
        timeSlotConfirmed.setVisibility(View.GONE);
    }

    public void searchItemId(View v) {
        if (itemTypeSelected_Reservation == null) {
            error = "Please select a type";
            refreshErrorMessage();
        }
        switch (itemTypeSelected_Reservation) {
            case "album":
                searchAlbumId(v);
                break;
            case "book":
                searchBookId(v);
                break;
            case "movie":
                searchMovieId(v);
                break;
        }
    }

    public void searchAlbumId(View v) {
        final TextView itemId = (TextView) findViewById(R.id.reservation_item_id);
        final TextView itemTitle = (TextView) findViewById(R.id.reservation_itemTitle_Text);
        final TextView itemFoundId = (TextView) findViewById(R.id.reservation_itemId_Text);
        final TextView itemCreator = (TextView) findViewById(R.id.reservation_itemCreator_Text);
        final TextView itemReleaseDate = (TextView) findViewById(R.id.reservation_itemReleaseDate_Text);

        HttpUtils.get("album/?itemId=" + itemId.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                try {
                    itemTitle.setText(response.getString("title"));
                    itemFoundId.setText("Item ID: " + response.getString("itemId"));
                    itemCreator.setText("Creator: " + response.getJSONObject("creator").getString("firstName") + ", " + response.getJSONObject("creator").getString("lastName"));
                    itemReleaseDate.setText("Release date: " + response.getString("releaseDate"));
                    itemTitle.setVisibility(View.VISIBLE);
                    itemFoundId.setVisibility(View.VISIBLE);
                    itemCreator.setVisibility(View.VISIBLE);
                    itemReleaseDate.setVisibility(View.VISIBLE);
                    currentItemId = response.getString(("itemId"));
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });
    }

    public void searchBookId(View v) {
        final TextView itemId = (TextView) findViewById(R.id.reservation_item_id);
        final TextView itemTitle = (TextView) findViewById(R.id.reservation_itemTitle_Text);
        final TextView itemFoundId = (TextView) findViewById(R.id.reservation_itemId_Text);
        final TextView itemCreator = (TextView) findViewById(R.id.reservation_itemCreator_Text);
        final TextView itemReleaseDate = (TextView) findViewById(R.id.reservation_itemReleaseDate_Text);

        HttpUtils.get("book/?itemId=" + itemId.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                try {
                    itemTitle.setText(response.getString("title"));
                    itemFoundId.setText("Item ID: " + response.getString("itemId"));
                    itemCreator.setText("Creator: " + response.getJSONObject("creator").getString("firstName") + ", " + response.getJSONObject("creator").getString("lastName"));
                    itemReleaseDate.setText("Release date: " + response.getString("releaseDate"));
                    itemTitle.setVisibility(View.VISIBLE);
                    itemFoundId.setVisibility(View.VISIBLE);
                    itemCreator.setVisibility(View.VISIBLE);
                    itemReleaseDate.setVisibility(View.VISIBLE);
                    currentItemId = response.getString(("itemId"));
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });
    }

    public void searchMovieId(View v) {
        final TextView itemId = (TextView) findViewById(R.id.reservation_item_id);
        final TextView itemTitle = (TextView) findViewById(R.id.reservation_itemTitle_Text);
        final TextView itemFoundId = (TextView) findViewById(R.id.reservation_itemId_Text);
        final TextView itemCreator = (TextView) findViewById(R.id.reservation_itemCreator_Text);
        final TextView itemReleaseDate = (TextView) findViewById(R.id.reservation_itemReleaseDate_Text);

        HttpUtils.get("movie/?itemId=" + itemId.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                try {
                    itemTitle.setText(response.getString("title"));
                    itemFoundId.setText("Item ID: " + response.getString("itemId"));
                    itemCreator.setText("Creator: " + response.getJSONObject("creator").getString("firstName") + ", " + response.getJSONObject("creator").getString("lastName"));
                    itemReleaseDate.setText("Release date: " + response.getString("releaseDate"));
                    itemTitle.setVisibility(View.VISIBLE);
                    itemFoundId.setVisibility(View.VISIBLE);
                    itemCreator.setVisibility(View.VISIBLE);
                    itemReleaseDate.setVisibility(View.VISIBLE);
                    currentItemId = response.getString(("itemId"));
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });
    }

    public void onItemReservationRadioButton(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.reserve_select_album:
                if (checked) itemTypeSelected_Reservation = "album";
                break;
            case R.id.reserve_select_book:
                if (checked) itemTypeSelected_Reservation = "book";
                break;
            case R.id.reserve_select_movie:
                if (checked) itemTypeSelected_Reservation = "movie";
                break;

        }
    }

    public void reserveSelectedItem(View v) {
        final TextView itemId = (TextView) findViewById(R.id.reservation_item_id);
        final TextView itemTitle = (TextView) findViewById(R.id.reservation_itemTitle_Text);
        final TextView itemFoundId = (TextView) findViewById(R.id.reservation_itemId_Text);
        final TextView itemCreator = (TextView) findViewById(R.id.reservation_itemCreator_Text);
        final TextView itemReleaseDate = (TextView) findViewById(R.id.reservation_itemReleaseDate_Text);
        final TextView reservationConfirm = (TextView) findViewById(R.id.reservation_Confirm_text);
        HttpUtils.post("onlineuser/reserve/username/" + currentUser + "?itemIds=" + currentItemId + "&timeSlotId=" + currentTimeSlot, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                itemId.setText("");
                itemTitle.setText("");
                itemFoundId.setText("");
                itemCreator.setText("");
                itemReleaseDate.setText("");
                itemTitle.setVisibility(View.GONE);
                itemFoundId.setVisibility(View.GONE);
                itemCreator.setVisibility(View.GONE);
                itemReleaseDate.setVisibility(View.GONE);
                try {
                    reservationConfirm.setText("Reservation successful: " + response.getString("reservationId"));
                    reservationConfirm.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });
    }

    public void searchReservationId(View v) {
        final Spinner reservationSpinner = (Spinner) findViewById(R.id.reservationSpinner);
        String reservationId = reservationSpinner.getSelectedItem().toString();
        final TextView reservationIDText = (TextView) findViewById(R.id.reservation_reservationId_Text);
        final TextView reservationItemTitle = (TextView) findViewById(R.id.reservation_reservationItemTitle_Text);
        final TextView reservationItemId = (TextView) findViewById(R.id.reservation_reservationItemId_Text);
        final TextView reservationItemReleaseDate = (TextView) findViewById(R.id.reservation_reservationItemReleaseDate_Text);
        final TextView reservationItemReturnDateandTime = (TextView) findViewById(R.id.reservation_reservationReturnDateandTime_Text);

        HttpUtils.get("reservation/" + reservationId, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                try {
                    reservationIDText.setText("Reservation ID: " + response.getString("reservationId"));
                    reservationItemTitle.setText(response.getJSONArray("items").getJSONObject(0).getString("title"));
                    reservationItemId.setText("Item ID: " + response.getJSONArray("items").getJSONObject(0).getString("itemId"));
                    reservationItemReleaseDate.setText("Item release date: " + response.getJSONArray("items").getJSONObject(0).getString("releaseDate"));
                    reservationItemReturnDateandTime.setText("Return by " + response.getJSONObject("timeSlot").getString("endDate") +
                            ", " + response.getJSONObject("timeSlot").getString("endTime"));
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                reservationIDText.setVisibility(View.VISIBLE);
                reservationItemTitle.setVisibility(View.VISIBLE);
                reservationItemId.setVisibility(View.VISIBLE);
                reservationItemReleaseDate.setVisibility(View.VISIBLE);
                reservationItemReturnDateandTime.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });
    }

    public void selectReturnDate(View v) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar c = Calendar.getInstance();
        String startDate = dateFormat.format(c.getTime());
        String startTime = timeFormat.format(c.getTime());
        String endTime = "11:59:59";
        final TextView endYear = (TextView) findViewById(R.id.reservation_year);
        final TextView endMonth = (TextView) findViewById(R.id.reservation_month);
        final TextView endDay = (TextView) findViewById(R.id.reservation_day);
        String endDate = endYear.getText().toString() + "-" + endMonth.getText().toString() + "-" + endDay.getText().toString();
        HttpUtils.post("timeSlot/create?startTime=" + startTime + "&endTime=" + endTime + "&startDate=" + startDate + "&endDate=" + endDate, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                endYear.setText("");
                endMonth.setText("");
                endDay.setText("");
                try {
                    currentTimeSlot = response.getString("timeSlotId");
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                TextView timeSlotConfirmed = (TextView) findViewById(R.id.reservation_returnDateConfirm_text);
                timeSlotConfirmed.setText("Return date selected: " + endDate);
                timeSlotConfirmed.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                try {
                    error += errorResponse;
                } catch (Exception e) {
                    error += e.getMessage();
                }
                System.out.println(error);
                refreshErrorMessage();
            }
        });

    }
    public void goToBrowseItems(View v) {
        setContentView(R.layout.login_page);
    }

    public void toLogout(View v) {
        currentUser = null;
        setContentView(R.layout.login_page);
    }
//    public void toggleIcon(View v){
//        final ImageView img = (ImageView) findViewById(R.id.mButton);
//        img.setImageResource(R.drawable.baseline_mystery_alt_24);
//    }
}