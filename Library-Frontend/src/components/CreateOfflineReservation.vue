<template>
  <div id="body">
    <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
    <div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-10 mt-3 mb-3">
          <h5>Select Return Date</h5>
          <input class="text-field" type="text" v-model="year" placeholder="Year">
          <input class="text-field" type="text" v-model="month" placeholder="month (1-12)">
          <input class="text-field" type="text" v-model="day" placeholder="Day">
          <button @click="createTimeSlot(year, month, day)" class="btn">Select</button>
          <p>
            <span v-if="selected_timeslot" style="color:green">{{ selected_timeslot }}</span>
          </p> 
        </div>
      </div>
      <div class="row d-flex justify-content-center">
        <div class="col-md-5 mt-3">
            <input class="text-field" type="text" v-model="userId" placeholder="User ID">
            <br>
            <br>
            <h5>Search Items</h5>
            <input class="text-field" type="text" v-model="item_query" placeholder="Search Item...">
            <i class="bi-search search-icon" v-bind:diabled="!item_query" @click="searchItem(item_query)"></i>
            <li class="item-list left-align" v-for="item in item_response" :key="item.itemId">
                <b>{{item.title}}</b><br>
                <ul>
                  <li><b><i>ID:</i></b> &ensp;{{item.itemId}}</li>
                  <li><b><i>Creator:</i></b> &ensp;{{item.creator.lastName}}, {{item.creator.firstName}}</li>
                  <li><b><i>Release Date:</i></b> &ensp;{{item.releaseDate}}</li>
                </ul>
                <button @click="reserveItem(item, userId)" class="btn small-btn">Reserve</button>
                <hr>
            </li>
        </div>
      <div class="row">
        <div class="col-md-3 mt-3 mr-3 left-col">
            <button @click="getAllAlbums()" class="btn">Browse Albums</button>
            <li class="item-list left-align" v-for="item in all_albums" :key="item.itemId">
                <b>{{item.title}}</b><br>
                <ul>
                  <li><b><i>ID:</i></b> &ensp;{{item.itemId}}</li>
                  <li><b><i>Creator:</i></b> &ensp;{{item.creator.lastName}}, {{item.creator.firstName}}</li>
                  <li><b><i>Release Date:</i></b> &ensp;{{item.releaseDate}}</li>
                  <li><b><i>Songs:</i></b> &ensp;{{item.numSongs}}</li>
                </ul>
                <button @click="reserveItem(item)" class="btn small-btn">Reserve</button>
                <hr>
            </li>
        </div>
        <div class="col-md-3 mt-3 mr-4 ml-3">
            <button @click="getAllBooks()" class="btn">Browse Books</button>
            <li class="item-list left-align" v-for="item in all_books" :key="item.itemId">
                <b>{{item.title}}</b><br>
                <ul>
                  <li><b><i>ID:</i></b> &ensp;{{item.itemId}}</li>
                  <li><b><i>Creator:</i></b> &ensp;{{item.creator.lastName}}, {{item.creator.firstName}}</li>
                  <li><b><i>Release Date:</i></b> &ensp;{{item.releaseDate}}</li>
                  <li><b><i>Pages:</i></b> &ensp;{{item.numPages}}</li>
                </ul>
                <button @click="reserveItem(item)" class="btn small-btn">Reserve</button>
                <hr>
            </li>
        </div>
        <div class="col-md-3 mt-3 mr-4 ml-3">
            <button @click="getAllMovies()" class="btn">Browse Movies</button>
            <li class="item-list left-align" v-for="item in all_movies" :key="item.itemId">
                <b>{{item.title}}</b><br>
                <ul>
                  <li><b><i>ID:</i></b> &ensp;{{item.itemId}}</li>
                  <li><b><i>Creator:</i></b> &ensp;{{item.creator.lastName}}, {{item.creator.firstName}}</li>
                  <li><b><i>Release Date:</i></b> &ensp;{{item.releaseDate}}</li>
                  <li><b><i>Duration:</i></b> &ensp;{{item.duration}}</li>
                </ul>
                <button @click="reserveItem(item)" class="btn small-btn">Reserve</button>
                <hr>
            </li>
        </div>
        <div class="col-md-3 mt-3 ml-3 right-col">
            <button @click="getAllNewspapers()" class="btn">Browse Newspapers</button>
            <li class="item-list left-align" v-for="item in all_newspapers" :key="item.itemId">
                <b>{{item.title}}</b><br>
                <ul>
                  <li><b><i>ID:</i></b> &ensp;{{item.itemId}}<br></li>
                  <li><b><i>Creator:</i></b> &ensp;{{item.creator.lastName}}, {{item.creator.firstName}}</li>
                  <li><b><i>Release Date:</i></b> &ensp;{{item.releaseDate}}</li>
                </ul>
                <button @click="reserveItem(item)" class="btn small-btn">Reserve</button>
                <hr>
            </li>
        </div>
      </div>  
      </div>
      <div class="row d-flex justify-content-center">
        <div class="col-md-3 mt-3">
           <button @click="getReservedItems()" class="btn">Reserved Items</button>
            <li v-for="item in reserved_items" :key="item.itemId">
                <b>{{item.title}}</b><br>
                <ul>
                  <li><b><i>ID:</i></b> &ensp;{{item.itemId}}</li>
                  <li><b><i>Creator:</i></b> &ensp;{{item.creator.lastName}}, {{item.creator.firstName}}</li>
                  <li><b><i>Release Date:</i></b> &ensp;{{item.releaseDate}}</li>
                </ul>
                <button @click="removeItem(item, userId)" class="btn small-btn">Remove</button>
                <hr>
            </li>
        </div>
      </div>
      <div class="row d-flex justify-content-center">
        <div class="col-xl-12 mt-3">
            <button @click="gotoOfflineUser()" class="btn"><i class="bi bi-arrow-left"></i></button>
        </div>
    </div>          
   </div>
    <p>
      <span v-if="error" style="color:red">{{ error }}</span>
    </p>
    <p>
      <span v-if="alert" style="color:green">{{ alert }}</span>
    </p> 
  </div>    
</template>
<script src="./createOfflineReservation.js"></script>
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

.left-align {
  text-align: left;
}

.bi {
  font-size: 18px;
}

.item-list {
  font-size: 11px;
}

.left-col {
  margin-left: -70px;
}

.right-col {
  margin-right: -70px;
}

.small-btn {
  font-size: 12px !important;
  margin: 2px;
}
</style>