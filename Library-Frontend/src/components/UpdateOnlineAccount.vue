<template>
  <div id="body">
    <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
    <div class = "container">
             <div class="row d-flex justify-content-center">
                 <div class="col-md-10 mt-3 mb-3">
                    <h5>Update Username</h5>
                    <input class="text-field" type="text" v-model="username3" placeholder="Username">
                    <input class="text-field" type="text" v-model="password3" placeholder="Password">
                    <input class="text-field" type="text" v-model="newUser" placeholder="New Username">
                    <button @click="updateUsername(username3, password3, newUser)" class="btn">Select</button>
                 </div>
             </div
             <div class="row d-flex justify-content-center">
                 <div class="col-md-10 mt-3 mb-3">
                    <h5>Update Password</h5>
                    <input class="text-field" type="text" v-model="username2" placeholder="Username">
                    <input class="text-field" type="text" v-model="password2" placeholder="Password">
                    <input class="text-field" type="text" v-model="newPass" placeholder="New Password">
                    <button @click="updatePassword(username2,password2,newPass)" class="btn">Select</button>
                 </div>
             </div>
             <div class="row d-flex justify-content-center">
                 <div class="col-md-10 mt-3 mb-3">
                    <h5>Update User Account Information</h5>
                    <input class="text-field" type="text" v-model="firstName" placeholder="First Name">
                    <input class="text-field" type="text" v-model="lastName" placeholder="Last Name">
                    <input class="text-field" type="text" v-model="address" placeholder="Address">
                    <label>Local:</label><input type="checkbox" class="text-field" v-model="isLocal">
                    <input class="text-field" type="text" v-model="username3" placeholder="Username">
                    <input class="text-field" type="text" v-model="password3" placeholder="Password">
                    <input class="text-field" type="text" v-model="email" placeholder="Email">
                    <button @click="updateOnlineUserAccount(firstName,lastName,address,isLocal,username3,password3,email)" class="btn">Select</button>
                 </div>
             </div>
             <div class="row d-flex justify-content-center">
                <div class="col-xl-12 mt-3">
                  <button @click="gotoOnlineUserView()" class="btn">Return</button>
             </div>
            </div>
        </div>    
    </div>
    </template>
  <script src = "./updateOnlineAccount.js"></script>
<style>
:root {
    --primary-color: #ED1B2F;
    --light-color: #EEF1EF;
    --dark-color: #262626;
    --dark-secondary: #414141;
    --font: "Roboto"
  }
  #body {
    font-family: var(--font);
    color: var(--light);
    background: var(--dark-color);
    height: 100vh;
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
</style>