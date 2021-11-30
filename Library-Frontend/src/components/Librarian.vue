<template>
  <div id="librarian">
     <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
   <div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-3 mt-5">
        <br>
          <h1 class="mt-5">View Weekly Schedule</h1>
          <button @click="gotoLibraryHour()" class="btn">Go to Schedule</button>
          
          <h1>Get All Offline Users</h1>
          <button @click="getOfflineUsers()" class="btn">Get All Offline Users</button>
          <li v-for="offlineUser in allOfflineUsers" :key="offlineUser.userId">
                <b>{{offlineUser.lastName}}, {{offlineUser.firstName}}, {{offlineUser.userId}}</b>
          </li>
          <h1 class="mt-5">Get Offline User</h1>
          <input class="text-field" type="text" v-model="id_query" placeholder="Offline User ID">
          <i class="bi-search search-icon" v-bind:diabled="!id_query" @click="searchOfflineUser(id_query)"></i>
          <li v-if="id_response">
                <b>{{id_response.lastName}}, {{id_response.firstName}}, {{id_response.userId}}</b>
          </li>
          <br>
          <br>
          <br>
          <h1>Get All Creators</h1>
          <button @click="getCreators()" class="btn">Get All Creators</button>
          <li v-for="creator in allCreators" :key="creator.creatorId">
                <b>{{creator.lastName}}, {{creator.firstName}}, {{creator.creatorType}}, {{creator.creatorId}}</b>
          </li>
          <h1 class="mt-5">Create Creator</h1>
          <input class="text-field" type="text" v-model="creatorFirst" placeholder="First Name">
          <input class="text-field" type="text" v-model="creatorLast" placeholder="Last Name">
          <input class="text-field" type="text" v-model="creatorType" placeholder="Creator Type">
          <button v-bind:diabled="!creatorFirst||!creatorLast||!creatorType" @click="createCreator(creatorFirst, creatorLast, creatorType)" class="btn">Create Creator</button>
          
          
        </div>
      </div>            
   </div>
    <br>
    <br>
    <br>
    <br>
    <br>
   <div class="container">
   <div class="row">
    <div class="col-sm">
    <div class="card-body mt-3 mb-3">
    <h1>Create Album</h1>
    <input class="text-field" type="text" v-model="albumTitle" placeholder="Title">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="albumCreateIsReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="albumCreateIsArchive">
    <br>
    <input class="text-field" type="text" v-model="albumReleaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="numSongs" placeholder="Number of Songs">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="albumCreateAvailable">
    <br>
    <input class="text-field" type="text" v-model="albumGenre" placeholder="Genre">
    <input class="text-field" type="text" v-model="albumCreatorId" placeholder="Creator ID">
    <button v-bind:diabled="!albumTitle||!albumReleaseDate||!numSongs||!albumGenre||!albumCreatorId" @click="createAlbum(albumTitle, albumCreateIsReservable, albumCreateIsArchive, albumReleaseDate, numSongs, albumCreateAvailable, albumGenre, albumCreatorId)" class="btn">Create Album</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Update Album</h1>
    <input class="text-field" type="text" v-model="albumUpdateId" placeholder="Item ID">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="albumUpdateIsReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="albumUpdateIsArchive">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="albumUpdateAvailable">
    <br>
    <button v-bind:diabled="!albumUpdateId" @click="updateAlbum(albumUpdateId, albumCreateIsReservable, albumCreateIsArchive, albumCreateAvailable)" class="btn">Update Album</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Remove Album</h1>
    <input class="text-field" type="text" v-model="albumDeleteId" placeholder="Item ID">
    <button v-bind:diabled="!albumDeleteId" @click="deleteAlbum(albumDeleteId)" class="btn">Remove Album</button>
    <br>
    </div>
    </div>
    <div class="col-sm">
    <div class="card-body mt-3 mb-3">
    <h1>Create Book</h1>
    <input class="text-field" type="text" v-model="bookTitle" placeholder="Title">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="bookCreateIsReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="bookCreateIsArchive">
    <br>
    <input class="text-field" type="text" v-model="bookReleaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="numPages" placeholder="Number of Pages">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="bookCreateAvailable">
    <br>
    <input class="text-field" type="text" v-model="bookGenre" placeholder="Genre">
    <input class="text-field" type="text" v-model="bookCreatorId" placeholder="Creator ID">
    <button v-bind:diabled="!bookTitle||!bookReleaseDate||!numPages||!bookGenre||!bookCreatorId" @click="createBook(bookTitle, bookCreateIsReservable, bookCreateIsArchive, bookReleaseDate, numPages, bookCreateAvailable, bookGenre, bookCreatorId)" class="btn">Create Book</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Update Book</h1>
    <input class="text-field" type="text" v-model="bookUpdateId" placeholder="Item ID">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="bookUpdateIsReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="bookUpdateIsArchive">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="bookUpdateAvailable">
    <br>
    <button v-bind:diabled="!bookUpdateId" @click="updateBook(bookUpdateId, bookUpdateIsReservable, bookUpdateIsArchive, bookUpdateAvailable)" class="btn">Update Album</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Remove Book</h1>
    <input class="text-field" type="text" v-model="bookDeleteId" placeholder="Item ID">
    <button v-bind:diabled="!bookDeleteId" @click="deleteBook(bookDeleteId)" class="btn">Remove Book</button>
    <br>
    </div>
    </div>
    <div class="col-sm">
    <div class="card-body mt-3 mb-3">
    <h1>Create Movie</h1>
    <input class="text-field" type="text" v-model="movieTitle" placeholder="Title">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="movieCreateIsReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="movieCreateIsArchive">
    <br>
    <input class="text-field" type="text" v-model="movieReleaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="duration" placeholder="Duration">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="movieCreateIsAvailable">
    <br>
    <input class="text-field" type="text" v-model="movieGenre" placeholder="Genre">
    <input class="text-field" type="text" v-model="movieCreatorId" placeholder="Creator ID">
    <button v-bind:diabled="!movieTitle||!movieReleaseDate||!duration||!movieGenre||!movieCreatorId" @click="createMovie(movieTitle, movieCreateIsReservable, movieCreateIsArchive, movieCreateIsAvailable, movieReleaseDate, duration, movieGenre, movieCreatorId)" class="btn">Create Movie</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Update Movie</h1>
    <input class="text-field" type="text" v-model="movieUpdateId" placeholder="Item ID">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="movieUpdateIsReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="movieUpdateIsArchive">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="movieUpdateIsAvailable">
    <br>
    <button v-bind:diabled="!movieUpdateId" @click="updateMovie(movieUpdateId, movieUpdateIsReservable, movieUpdateIsArchive, movieUpdateIsAvailable)" class="btn">Update Movie</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Remove Movie</h1>
    <input class="text-field" type="text" v-model="movieDeleteId" placeholder="Item ID">
    <button v-bind:diabled="!movieDeleteId" @click="deleteMovie(movieDeleteId)" class="btn">Remove Movie</button>
    <br>
    </div>
    </div>
    <div class="col-sm">
    <div class="card-body mt-3 mb-3">
    <h1>Create Newspaper</h1>
    <input class="text-field" type="text" v-model="newspaperCreateTitle" placeholder="Title">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="newspaperCreateIsArchive">
    <br>
    <input class="text-field" type="text" v-model="newspaperCreateReleaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="newspaperCreateCreatorId" placeholder="Creator ID">
    <button v-bind:diabled="!newspaperCreateTitle||!newspaperCreateReleaseDate||!newspaperCreateCreatorId" @click="createNewspaper(newspaperCreateTitle, newspaperCreateIsArchive, newspaperCreateReleaseDate, newspaperCreateCreatorId)" class="btn">Create Newspaper</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Update Newspaper</h1>
    <input class="text-field" type="text" v-model="newspaperUpdateId" placeholder="Item ID">
    <input class="text-field" type="text" v-model="newspaperUpdateTitle" placeholder="Title">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="newspaperUpdateIsArchive">
    <br>
    <input class="text-field" type="text" v-model="newspaperUpdateReleaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="newspaperUpdateCreatorId" placeholder="Creator ID">
    <button v-bind:diabled="!newspaperUpdateId||!newspaperUpdateTitle||!newspaperUpdateReleaseDate||!newspaperUpdateCreatorId" @click="updateNewspaper(newspaperUpdateId, newspaperUpdateTitle, newspaperUpdateIsArchive, newspaperUpdateReleaseDate, newspaperUpdateCreatorId)" class="btn">Update Newspaper</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Remove Newspaper</h1>
    <input class="text-field" type="text" v-model="newspaperDeleteId" placeholder="Item ID">
    <button v-bind:diabled="!newspaperDeleteId" @click="deleteNewspaper(newspaperDeleteId)" class="btn">Remove Newspaper</button>
    <br>
    </div>
    </div>
    </div>
    </div>
<div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-3 mt-5">
        <br>
            <button class="btn mt-3 mb-5" @click="gotoOfflineUserView()">Offline User Services</button>
            <br>
            <button @click="logout()" class="btn">Logout</button>
          <br>
        </div>
        <div class="row d-flex justify-content-center">
          <div class="col-xl-12 mt-3 mb-3">
            <button @click="logout()" class="btn">Logout</button>
          </div>
      </div>
      </div>            
  </div>
  <div>
    <h3>Set up new head librarian account</h3>
                  <div class="card-body">
                    <input class="text-field" type="text" v-model="firstName" placeholder="First Name"><br>
                    <input class="text-field" type="text" v-model="lastName" placeholder="Last Name"><br>
                    <input class="text-field" type="text" v-model="address" placeholder="Address"><br>
                    <input class="text-field" type="text" v-model="username" placeholder="Username"><br>
                    <input class="text-field" type="text" v-model="password" placeholder="Password"><br>
                    <input class="text-field" type="text" v-model="email" placeholder="Email"><br>
                    <button v-bind:diabled="!firstName||!lastName||!address||!username||!password||!email" @click="signUpLibrarian(firstName, lastName, address, username, password, email)" class="btn">Signup</button>
                  </div>
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
    height: 280vh;
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