# Locations
<strong>RPG Locations</strong>
<p>
Spigot plugin. The plugin adds locations like in PRG games.
Provided fast travel and location opening system with custom events.
</p>

<h3>Localization</h3>
<p>
To add a language you need to download the language .loc file and move it to the plugin/Locations/locales/ folder. If you want to use the language as the default (which means that all player who have unsupported languages will see this language) you have to open the locale file and after a comma add the word "default", then do /alocations load locales to load the locale. Also you might add any languages as supported by the locale by adding language code into the "language" field and splitting them with commas. (ex. language=en_us, en_ud) [<a href="https://minecraft.gamepedia.com/Language">all available language codes</a>]
</p>
<strong>Locales</strong>
<ul>
  <li><a href="https://drive.google.com/file/d/1MGDF8fIuIog0tl9aRATjuCGRGqD53TCc/view">Russian</li>
</ul>

# Commands
<h3>Fast travel</h3>
/travel <<i>location-name</i>> [<i>nickname</i>]
<h3>Administration</h3>
<h4>Data control <font size="11px">(<i><a href="#save-load">more</a></i>)</font></h4> 
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
/alocations set priority <<i>location-name</i>> <<i>priority</i>> (<i><a href="#priority">more</a></i>)<br/>
/alocations set position <<i>location-name</i>> <<i>first|second</i>> (<i><a href="#pos-warp">more</a></i>) <br/>
/alocations set warp <<i>location-name</i>> (<i><a href="#pos-warp">more</a></i>) <br/>
<h4>Database control</h4>
/alocations database check <br/>
/alocations database info <br/>
<h4>Database setup</h4>
/alocations database set database <<i><a href="#databases">database</a></i>> <br/>
/alocations database set username <<i>username</i>> <br/>
/alocations database set password <<i>password</i>> <br/>
/alocations database set url <<i>url</i>> <br/>
/alocations database set use <<i>yes|no</i>> <br/>

# Permissions
/alocations - ksviety.locations.admin <br/>
/travel - ksviety.locations.travel <br/>
use /travel for other players - ksviety.locations.travel.others <br/>
see all existing locations in /travel - ksviety.locations.list 

# Info
<h2>Save and load</h2>
<h4 id="save-load">Saving/loading data</h4>
<p>
  <strong>/alocations save </strong><br/> 
  Stores chosen data to the local storage or database if enabled. <br/>
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
<strong id="data">Provided data types: </strong><i>locations, players, dbconf, locales ("all" to save/load everything)</i>

<h2 id="#types">Location types</h2>
<p>
  At the moment the value might be left on as currently it does not affect on anything at all. Planned to be used for something in   future.
</p>
<strong>Currently available types</strong>
<ul>
  <li>city</li>
  <li>village</li>
  <li>dungeon</li>
  <li>house</li>
  <li>store</li>
  <li>other</li>
</ul>

<h2 id="#priority">Location priority</h2>
<p>
  Does not affect on anything at the moment. <br/>
  The priority value must be an integer.
</p>

<h2 id="pos-warp">Location positions/warp</h2>
<h3>Positions</h3>
<p>
  Sets chosen position of the location to your current position in the world - generates a cube collider of the location by the scheme below. <br/>
  <img src="https://github.com/ksviety/ksviety.github.io/blob/master/9qPLw.jpg" /> <br/>
  <strong>Available positions</strong> <br/>
  <ul>
    <li>first</li>
    <li>second</li>
  </ul>
</p>
<h3>Warp</h3>
<p>
  Warp is the point where a player that used fast travel is gonna be appeared. The command sets the warp position of the location to your current position in the world. Also might be set via console with specified coordinates after the location name. The warp should be set inside the location because when a player used fast travel it calls the PlayerEntredLocationEvent event.
</p>

<h2 id="database">Database setup</h2>
<p>
  Database might be confugured from the game via the commands or in file 'dbconf.json' in the plugin folder. If the file does not exist you have to do '/alocations save dbconf', it will store the default database configurations (if you had already setup the database, it will save the current datas).
</p>
<strong>Currently supported databases:</strong> <br/>
<ul>
  <li>mysql</li>
</ul>
<h3>Setting up database via the commands (<i>ex.</i>)</h3>
<p>
  /alocations database set database mysql <br/>
  /alocations database set username root <br/>
  /alocations database set password 123 <br/>
  /alocations database set url jdbc:mysql://localhost:3306/database <br/>
  /alocations database check (<i color="lightgray"><font size="8">Checking whether the connection is success.</font></i>) <br/>
  /alocations database set use yes <br/>
</p>

# API

<h2>Events</h2>
<strong>Package: </strong>me.ksviety.plugins.mc.locations.events <br/>
<strong>Provided events:</strong>
<ul>
  <li>
    <strong>PlayerEntredLocationEvent</strong> <br/>
    <p>
      Called when a player entered a location. <br/>
      Extends: <i>PlayerEvent</i> <br/>
      Cancellable: <i>yes</i> <br/>
      Provides:
      <ul>
        <li>The location that was entered by the player.</li>
      </ul>
    </p>
  </li>
  <li><strong>PlayerLeftLocationEvent</strong> <br/>
    <p>
      Extends: <i>PlayerEvent</i> <br/>
      Cancellable: <i>yes</i> <br/>
      Provides:
      <ul>
        <li>The location that was left by the player.</li>
      </ul>
    </p>
  </li>
  <li><strong>PlayerOpenedLocationEvent</strong> <br/> 
    <p>
      Extends: <i>PlayerEvent</i> <br/>
      Cancellable: <i>yes</i> <br/>
      Provides:
      <ul>
        <li>The location that was opened by the player.</li>
      </ul>
    </p>
  </li>
  <li><strong>PlayerTraveledEvent</strong> <br/>
    <p>
      Extends: <i>PlayerEvent</i> <br/>
      Cancellable: <i>yes</i> <br/>
      Provides:
      <ul>
        <li>The location the player traveled to.</li>
      </ul>
    </p>
  </li>
</ul>










