<template>
  <div id="librarian">
      <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
        <div class="container-fluid">
          <a class="navbar-header">Library</a>
        </div>
      </nav>
  <div class="container">
    <div class="row d-flex justify-content-center">
      <div class="col mt-5">
        <h1>Librarian</h1>
        <div class="container">
          <div class="row">
            <div class="col-6">
              <div class="row d-flex justify-content-center">
                <div class="col">
                  <h3>Remove librarian account</h3>
                  <div class ="card-body">
                    <input class="text-field" type="text" v-model="delId" placeholder="ID"><br>
                    <button v-bind:disabled="!delId" @click="removeLibrarian(delId)" class="btn">Delete account</button>
                  </div>
                  <h3>Set up new librarian account</h3>
                  <div class="card-body">
                    <input class="text-field" type="text" v-model="firstName" placeholder="First Name"><br>
                    <input class="text-field" type="text" v-model="lastName" placeholder="Last Name"><br>
                    <input class="text-field" type="text" v-model="address" placeholder="Address"><br>
                    <input class="text-field" type="text" v-model="username" placeholder="Username"><br>
                    <input class="text-field" type="text" v-model="password" placeholder="Password"><br>
                    <input class="text-field" type="text" v-model="email" placeholder="Email"><br>
                    <button v-bind:disabled="!firstName||!lastName||!address||!username||!password||!email" @click="signUpLibrarian(firstName, lastName, address, username, password, email)" class="btn">Signup</button>
                  </div>
                </div>  
              </div>
              <h3>Update account</h3>
              <div class="card-body">
                <input class="text-field" type="text" v-model="nwfirstName" placeholder="First Name"><br>
                <input class="text-field" type="text" v-model="nwlastName" placeholder="Last Name"><br>
                <input class="text-field" type="text" v-model="nwaddress" placeholder="Address"><br>
                <input class="text-field" type="text" v-model="nwusername" placeholder="Username"><br>
                <input class="text-field" type="text" v-model="nwemail" placeholder="Email"><br>
                <label>isHead:</label><input type="checkbox" class="text-field" v-model="nwisHead"><br>
                <button @click="updateLibrarian(nwfirstName, nwlastName, nwaddress, nwusername, nwemail,nwisHead)" class="btn">Save & Update</button>
              </div>
              <h3>Change password</h3>
                <input class="text-field" type="text" v-model="oldPassword" placeholder="Old Password"><br>
                <input class="text-field" type="text" v-model="newPassword" placeholder="New Password"><br>
                <button v-bind:disabled="!oldPassword||!newPassword" @click="changePassword(oldPassword, newPassword)" class="btn">Change Password</button>   
            </div>
            <div class="col-6">
              <h3>Search librarian</h3>
                  <table>
                    <tr>
                      <td>
                        <input class="text-field" type="text" v-model="librarianUsername" placeholder="Librarian Username">
                      </td>
                      <td>
                        <button class="btn" @click="findLibrarian(librarianUsername)">Search</button>
                      </td>
                    </tr>
                  </table>
                  <div class="container">
                    <div class="row align-left align-content-start">
                      <h5>Found results:</h5><br>
                    </div>
                    <div class="row align-left align-content-start">
                      <b v-if="librarian">{{ librarian.firstName }}</b>
                      <v-divider verticle></v-divider>
                      <b v-if="librarian">&nbsp;{{ librarian.lastName }}</b>
                    </div>
                    <div class="row align-left no-gutters">
                      <b>ID:&ensp;</b>
                      <v-divider verticle></v-divider>
                      <p v-if="librarian">{{ librarian.id }}</p>
                    </div>
                    <div class="row align-left no-gutters">
                      <b>Username:&ensp;</b>
                      <v-divider verticle></v-divider>
                      <p v-if="librarian">{{ librarian.username }}</p>
                    </div>
                    <div class="row align-left no-gutters">
                      <b>Address:&ensp;</b>
                      <v-divider verticle></v-divider>
                      <p v-if="librarian">{{ librarian.address }}</p>
                    </div>
                    <div class="row align-left no-gutters">
                      <b>Email:&ensp;</b>
                      <v-divider verticle></v-divider>
                      <p v-if="librarian">{{ librarian.email }}</p>
                    </div>
                  </div>
              <h3>All librarians</h3>
                <div>
                  <table>
                    <tr v-for="librarian in librarians">
                      <div class="row align-left align-content-start">
                        <b v-if="librarian">{{ librarian.firstName }}</b>
                        <v-divider verticle></v-divider>
                        <b v-if="librarian">&nbsp;{{ librarian.lastName }}</b>
                      </div>
                      <div class="row align-left no-gutters">
                        <b>ID:&ensp;</b>
                        <v-divider verticle></v-divider>
                        <p v-if="librarian">{{ librarian.id }}</p>
                      </div>
                      <div class="row align-left no-gutters">
                        <b>Username:&ensp;</b>
                        <v-divider verticle></v-divider>
                        <p v-if="librarian">{{ librarian.username }}</p>
                      </div>
                      <div class="row align-left no-gutters">
                        <b>Address:&ensp;</b>
                        <v-divider verticle></v-divider>
                        <p v-if="librarian">{{ librarian.address }}</p>
                      </div>
                      <div class="row align-left no-gutters">
                        <b>Email:&ensp;</b>
                        <v-divider verticle></v-divider>
                        <p v-if="librarian">{{ librarian.email }}</p>
                      </div>
                    </tr>
                  </table>
                  <button class="btn" @click="findAllLibrarians()">Refresh</button>
                </div>
            </div>
          </div>
        </div>  
      </div>        
    </div>            
  </div>
    <p>
      <span v-if="error" style="color:red">{{ error }}</span>
    </p>
  </div>    
</template>

<script src="./librarian.js"></script>
<style>
  :root {
    --primary-color: #ED1B2F;
    --light-color: #EEF1EF;
    --dark-color: #262626;
    --dark-secondary: #414141;
    --font: "Roboto"
  }
  #librarian {
    font-family: var(--font);
    color: var(--light);
    background: var(--dark-color);
  }
  .navbar-main {
    font-family: var(--font);
    font-size: 24px;
    background-color: var(--primary-color);
    height: 65px;
  }
  .text-field {
    text-align: center;
    padding: 6px;
    padding-top: 8px;
    padding-bottom: 8px;
    width: 200px;
    font-size: 15px;
    transition: 0.3s;
    background-color: var(--light-color);
    border-color: var(--dark-color);
    border: none;
    border-radius: 3em;
    margin: 5px;
  }
  .card {
    background-color: var(--dark-secondary);
  }
  .btn {
    color: var(--dark-color);
    background-color: var(--light-color);
    border-color: var(--dark-secondary);
    text-decoration: none;
    transition: 0.3s;
    margin:5px;
    padding-left: 25px !important;
    padding-right: 25px !important;
    border-radius: 3em !important;
  }

  .btn:hover, .btn:focus, .btn:active {
    color: var(--light-color);
    background-color: var(--primary-color);
    transition: 0.3s;
  }

  .regular-text {
    font-family: var(--font);

  }

  .search-icon {
    padding: 5px;
    font-size: 16px;
    width: 10px;
    text-align: center;
    text-decoration: none;
    border-radius: 100%;
    color: var(--primary-color);
    transition: 0.3s;
}

 .search-icon:hover, .search-icon:active, .search-icon:focus{
    padding: 5px;
    font-size: 16px;
    width: 10px;
    text-align: center;
    text-decoration: none;
    border-radius: 100%;
    color: var(--light-color);
    transition: 0.3s;
    cursor:pointer;
}
</style>