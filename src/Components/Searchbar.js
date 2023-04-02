import '../App.css';
import React, { useState } from 'react';

function Searchbar(properties) {
  const [searchTerm, setSearchTerm] = useState('');

  const handleInputChange = (event) => {
    setSearchTerm(event.target.value);
    properties.onSearch(event.target.value);
  }

  return (
    <div>
      <input type="text" placeholder="Search..." value={searchTerm} onChange={handleInputChange} />
    </div>
  );
}

export default Searchbar;
