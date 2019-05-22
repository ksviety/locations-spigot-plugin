# Locations
<strong>RPG Locations</strong>
<p>
Spigot pluign. The pluign adds locations like in PRG games.
Provided fast travel and location opening system with custom events.
</p>

# Commands
<h3>Fast travel</h3>
/travel <<i>location-name</i>> [<i>nickname</i>]
<h3>Administration</h3>
<h4>Data control</h4>
/alocations save <<i><a href="#data">data</a></i>> <br/>
/alocations load <<i><a href="#data">data</a></i>> <br/>
<h4>Location control</h4>
/alocations create <<i>location-name</i>> <br/>
/alocations remove <<i>location-name</i>> <br/>
/alocations info <<i>location-name</i>> <br/>
<h4>Location setup</h4>
/alocations set label <<i>location-name</i>> <<i>label</i>> <br/>
/alocations set type <<i>location-name</i>> <<i><a href="#types">type</a></i>> <br/>
/alocations set activity <<i>location-name</i>> <<i>active|inactive</i>> <br/>
/alocations set priority <<i>location-name</i>> <<i>priority</i>> <br/>
/alocations set position <<i>location-name</i>> <<i>first|second</i>> [<i>x y z</i>] <br/>
/alocations set warp <<i>location-name</i>> [<i>x y z</i>] <br/>
<h4>Database control</h4>
/alocations database check <br/>
/alocations database info <br/>
<h4>Database setup</h4>
/alocations database set database <<i><a href="#databases">database</a></i>> <br/>
/alocations database set username <<i>username</i>> <br/>
/alocations database set password <<i>password</i>> <br/>
/alocations database set url <<i>url</i>> <br/>
/alocations database set use <<i>yes|no</i>> <br/>

# Info
<h2>Save and load</h2>
<h4 id="save-load">Saving/loading data</h4>
<p>
  <strong>/alocations save </strong><br/> 
  Stores chosen data to the local storage or database if enabled.
  <strong>/alocations load </strong><br/>
  Loads chosen data from the local storage or database if enabled.
</p>
<h4 id="move-db-ls">Moving data between database and local storage</h4>
<strong>Local storage to database</strong>
<p>
  /alocations database use no <br/>
  /alocations load <<i><a href="#data">data</a></i>> <br/>
  /alocations database use yes <br/>
  /alocations save <<i><a href="#data">data</a></i>> <br/>
</p>
<strong>Database to local storage</strong>
<p>
  /alocations database use yes <br/>
  /alocations load <<i><a href="#data">data</a></i>> <br/>
  /alocations database use no <br/>
  /alocations save <<i><a href="#data">data</a></i>> <br/>
</p>
<strong id="data">Provided data types: </strong><i>locations, players, dbconf ("all" to save everything)</i>









