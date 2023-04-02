import React from 'react';
import '../App.css';
import {SidebarData} from './SidebarData'
import Searchbar from './Searchbar';

function Sidebar(){

    /*const [searchResults, setSearchResults] = useState([]);

    const handleSearch = (searchTerm) => {
      // API call happens here
      // and update the searchResults state variable
      setSearchResults([...]);
    }
    

    <div>
        <Searchbar onSearch={handleSearch} />
      <ul>
        {searchResults.map((result) => (
          <li key={result.id}>{result.name}</li>
        ))}
      </ul>
    </div>
    ^^ this will be used for the actual search bar once we have
    something to connect it to the one in place currently is a 
    place holder

    */

    return (
    <div className="Sidebar">
        <ul className="SidebarList">
            {SidebarData.map((val, key) => {
                return (
                <li 
                key={key}
                className="row"
                id={window.location.pathname == val.link ? "active" : ""}
                onClick={()=> {
                    window.location.pathname = val.link;
                }}
                > 
                <div id="title">
                    {val.title}
                </div> 
                </li>
                );
    })}
    </ul>
    
    <div className="search-filter-container">
        <button className="filter-button">Filter</button>
        <div className="search-bar-container">
            <Searchbar />
        </div>
    </div>



<div className="Timer">
<p>Timer</p>
      <span id="timer-hours">00
      </span>:<span id="timer-minutes">00
      </span>:<span id="timer-seconds">00
      </span>
</div>

    </div>
    )
}
export default Sidebar