<template>
  <div id="body">
    <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
    <div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-xl-12 mt-5">
          <button @click="gotoOfflineCreateReservation()" class="btn">
            Create Reservation
          </button>
        </div>
      </div>
      <div class="row d-flex justify-content-center">
        <div class="col-xl-12 mt-5">
            <button @click="gotoOfflineCreateEvent()" class="btn">Create Event</button>
            <br>
            <br>
        </div>
      </div>  
      <div class="row d-flex justify-content-center">
        <div class="col-xl-12 mt-3 mb-3">
          <button @click="logout()" class="btn">Exit</button>
        </div>
      </div>
    </div>
    <p>
      <span v-if="error" style="color:red">{{ error }}</span>
    </p>
  </div>
</template>
<script src="./offlineFunction.js"></script>
<style>
:root {
  --primary-color: #ed1b2f;
  --light-color: #eef1ef;
  --dark-color: #262626;
  --dark-secondary: #414141;
  --font: "Roboto";
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
  margin: 5px;
  padding-left: 25px !important;
  padding-right: 25px !important;
  border-radius: 3em !important;
}

.btn:hover,
.btn:focus,
.btn:active {
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

.search-icon:hover,
.search-icon:active,
.search-icon:focus {
  padding: 5px;
  font-size: 16px;
  width: 10px;
  text-align: center;
  text-decoration: none;
  border-radius: 100%;
  color: var(--light-color);
  transition: 0.3s;
  cursor: pointer;
}

.left-align {
  text-align: left;
}
</style>
