package ca.mcgill.ecse321.library_android;

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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String error = null;
    private String currentUser = null;
    private Long currentUserId = 0L;
    private Long timeSlotId = 0L;
    private boolean UserInfoOn = false;

    private void setCurrentUser(String username){
        this.currentUser = username;
    }

    private void setCurrentUserId(String userId) { this.currentUserId = Long.parseLong(userId); }

    private void setTimeSlotId(String timeSlotId) { this.timeSlotId = Long.parseLong(timeSlotId); }

    private String getCurrentUser(){
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

    public void loginUser(View v) {
        error = "";
        final TextView username = (TextView) findViewById(R.id.login_username);
        final TextView password = (TextView) findViewById(R.id.login_password);
        HttpUtils.post("onlineuser/login?username="+username.getText().toString()+"&password="+password.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                setCurrentUser(username.getText().toString());
                try {
                    setCurrentUserId(response.getString("userId"));
                } catch (JSONException e) {
                    error += e.getMessage();
                }
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

    public void Logout(View v) {
        error = "";
        currentUser = null;
        toLogin(v);
    }

    public void showPersonalInfo(View v) {
        setContentView(R.layout.manageaccount_page);
        TextView curError = (TextView)findViewById(R.id.manageaccount_error);
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
    }

    public void toAddEvent(View v) { setContentView(R.layout.addevent_page); }

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
        HttpUtils.post("onlineuser/create?firstName="+firstName.getText().toString()+"&lastName="+lastName.getText().toString()+"&address="+address.getText().toString()+"&isLocal="+isLocal.isChecked()+"&username="+username.getText().toString()+"&password="+password.getText().toString()+"&email="+email.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                setCurrentUser(username.getText().toString());
                try {
                    setCurrentUserId(response.getString("userId"));
                } catch (JSONException e) {
                    error += e.getMessage();
                }
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

    public void createTimeSlot(View v){
    error = "";
    final TextView startDate = (TextView) findViewById(R.id.addevent_startdate);
    final TextView endDate = (TextView) findViewById(R.id.addevent_enddate);
    final TextView startTime = (TextView) findViewById(R.id.addevent_starttime);
    final TextView endTime = (TextView) findViewById(R.id.addevent_endtime);

        HttpUtils.post("timeslot/create?startTime="+startTime.getText().toString()+"&endTime="+endTime.getText().toString()+"&startDate="+startDate.getText().toString()+"&endDate="+endDate.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                refreshErrorMessage();
                startDate.setText("");
                endDate.setText("");
                startTime.setText("");
                endTime.setText("");
                try {
                    setTimeSlotId(response.getString("timeSlotId"));
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

    public void createEvent(View v){
        error = "";
        final TextView name = (TextView) findViewById(R.id.addevent_name);
        final CheckBox isPrivate = (CheckBox) findViewById(R.id.addevent_private);
        final CheckBox isAccepted = (CheckBox) findViewById(R.id.addevent_accepted);

        HttpUtils.post("event/create?name="+name.getText().toString()+"&timeSlotId="+timeSlotId+"&isPrivate="+isPrivate.isChecked()+"&isAccepted="+isAccepted.isChecked()+"&userId="+currentUserId, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                refreshErrorMessage();
                name.setText("");
                isPrivate.setChecked(false);
                isAccepted.setChecked(false);
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

//    public void toggleIcon(View v){
//        final ImageView img = (ImageView) findViewById(R.id.mButton);
//        img.setImageResource(R.drawable.baseline_mystery_alt_24);
//    }
}