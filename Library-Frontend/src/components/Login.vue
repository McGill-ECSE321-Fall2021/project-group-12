<template>
  <div id="body">
    <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
   <div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-3 mt-5">
          <h1>Login</h1>
          <div class="card-body mt-3 mb-3">
              <input class="text-field" type="text" v-model="username" placeholder="Username">
              <label><input id="password-field" class="text-field" type="password" v-model="password" placeholder="Password"><i id="hidden-icon" class="bi bi-eye eye-icon" @click="togglePasswordVisibility()"></i></label>
              <button v-bind:disabled="!username||!password" @click="loginUser(username, password)" class="btn">Login</button>
          </div>
          <br>
          <a class="regular-text">Don't have an account?</a>
          <br>
          <button class="btn mt-3 mb-5" @click="gotoSignUp()">Sign Up</button>
        </div>
      </div>         
   </div>
    <p>
      <span v-if="error" style="color:red">{{ error }}</span>
    </p>
    <div class="row justify-content-center d-flex">
      <i id="egg" class="egg-icon bi bi-egg" @click="breakEgg()"></i>
    </div>
  </div>    
</template>
<script src="./login.js"></script>
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
    height: 200vh;
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

  .eye-icon {
    color: var(--light-color);
    cursor: pointer;
    transition: 0.3s;
  }

  .eye-icon:hover, .eye-icon:active, .eye-icon:focus {
    color: var(--primary-color);
    transition: 0.3s;
  }

  .egg-icon {
    font-size: 24px;
    position: absolute;
    bottom: -95vh;
    transition: 0.3s;
    cursor: pointer;
  }

  .egg-icon:hover, .egg-icon:active, .egg-icon:focus {
    transition: 0.3s;
    color: var(--primary-color);
  }
</style>