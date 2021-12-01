<template>
  <div id="body">
    <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
   <div class="container">
      <div class="row d-flex justify-content-center">
        <p><b>Password Requirements:</b> Password must be 8 characters long, have one capital letter and contain one special character (!,@,#,$,%,^,&,*,+,-,=,?,_)</p>
        <div class="col-md-3 mt-5">
          <h1>Sign Up</h1>
          <div class="card-body mt-3 mb-3">
            <input class="text-field" type="text" v-model="firstName" placeholder="First Name">
            <input class="text-field" type="text" v-model="lastName" placeholder="Last Name">
            <input class="text-field" type="text" v-model="address" placeholder="Address">
            <br>
            <label>Local:</label>
            <br>
            <input type="checkbox" class="text-field" v-model="isLocal">
            <input class="text-field" type="text" v-model="username" placeholder="Username">
            <input class="text-field" type="text" v-model="password" placeholder="Password">
            <input class="text-field" type="text" v-model="email" placeholder="Email">
            <button v-bind:disabled="!firstName||!lastName||!address||!username||!password||!email" @click="signUpUser(firstName, lastName, address, isLocal, username, password, email)" class="btn">Signup</button>
          </div>
          <br>
          <a class="regular-text">Already have an account?</a>
          <br>
          <button class="btn mt-3 mb-5" @click="gotoLogin()">Login</button>
        </div>
      </div>            
   </div>
    <p>
      <span v-if="error" style="color:red">{{ error }}</span>
    </p>
  </div>    
</template>
<script src="./signup.js"></script>
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
    height: 180vh;
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
