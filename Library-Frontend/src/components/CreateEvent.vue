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
                    <h5>Select Event Time</h5>
                    <input class="text-field" type="text" v-model="sDate" placeholder="Start Date : (YYYY-MM-DD)">
                    <input class="text-field" type="text" v-model="eDate" placeholder="End Date : (YYYY-MM-DD)">
                    <input class="text-field" type="text" v-model="sTime" placeholder="Start time : (HH:mm:ss)">
                    <input class="text-field" type="text" v-model="eTime" placeholder="End time : (HH:mm:ss)">

                    <button @click="createTimeSlot(sDate, eDate, sTime, eTime)" class="btn">Select</button>
                 </div>
             </div>
             <div class="row d-flex justify-content-center">
                 <div class="col-md-10 mt-3 mb-3">
                    <h5>Create Event at Specified Time</h5>
                    <input class="text-field" type="text" v-model="eventName" placeholder="Event Name:">
                    <label>Private:</label><input type="checkbox" class="text-field" v-model="isPrivate">
                    <label>Accepted:</label><input type="checkbox" class="text-field" v-model="isAccepted">
                    <button @click="createEvent(eventName, isPrivate, isAccepted)" class="btn">Select</button>
                 </div>
             </div>
        </div>    
    </div>  
</template>
<script src = "./createEvent.js"></script>
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