import React from "react";
import { Link } from "react-router-dom";
import PageFrame from "./PageFrame";
import axios from 'axios';
import Memo from '../memo/Memo'
import { useEffect, useState } from 'react';
import { FaSearch } from "react-icons/fa";


function MainDetail(memoList) {
    const [searchValue, setSearchValue] = useState('');
    const [searchMemos, setSearchMemos] = useState(memoList);

    const filter = (e) => {
        const keyword = e.target.value;
    
        if (keyword !== '') {
          const results = searchMemos.filter((memo) => {
            return memo.memoTitle.toLowerCase().startsWith(keyword.toLowerCase());
            // Use the toLowerCase() method to make it case-insensitive
          });
          setSearchMemos(results);
        } else {
            setSearchMemos(memoList);
          // If the text field is empty, show all memos
        }
    
        setSearchValue(keyword);
      };

    function deleteMemo(e, memoIdx) {
        e.preventDefault();
        console.log(e);
        const baseUrl = "http://localhost:9000/memo";
        const deleteMemo = async () =>{
        await axios
            .delete(baseUrl + "/" + memoIdx);
        }
        deleteMemo();

        const removeMemo = memoList.find(function(memo) {return memo.memoIdx === memoIdx});
        const index = memoList.indexOf(removeMemo);
        if (index > -1) memoList.splice(index, 1);
        console.log(memoList);
        console.log("삭제완료");
    }

    

    if (searchValue === '') {
        return (
            <div className="mainPageContainer">
                <div className="searchContainer">
                    <div className="iconBox">
                        <FaSearch />
                    </div>
                    <input
                        type="search"
                        value={searchValue}
                        onChange={filter}
                        className="inputBox"
                        placeholder="search"
                    />
                </div>
                <div className="memoListContainer">
                    {memoList.map((memo) => (
                        <div key = {memo.memoIdx} className="memoContainer">
                            <Memo memo={memo} key = {memo.memoIdx}/>
                            <div className="deleteMemo" onClick={(e) =>     {deleteMemo(e, memo.memoIdx)}}>
                            <button type="submit"> ❌ </button>
                        </div>
                    </div>))}
                </div>
                <Link to="/memo/new-memo" className="addBtnContainer">
                    <button className="addMemoBtn"> Add Memo </button>
                </Link>
            </div>
        )
    } else {
        return (
            <div className="mainPageContainer">
                <div className="searchContainer">
                <div className="iconBox">
                        <FaSearch />
                    </div>
                    <input
                        type="search"
                        value={searchValue}
                        onChange={filter}
                        className="inputBox"
                        placeholder="search"
                    />
                </div>
                
                <div className="memoListContainer">
                    {searchMemos && searchMemos.length > 0  ? (
                        searchMemos.map((memo) => (
                            <div key = {memo.memoIdx} className="memoContainer">
                                <Memo memo={memo} key = {memo.memoIdx}></Memo>
                                <div className="deleteMemo" onClick={(e) => {deleteMemo(e, memo.memoIdx)}}>
                                    <button type="submit"> ❌ </button>
                                </div>
                            </div>
                        ))) : (
                        <h1>No memo found!</h1>
                    )}
                </div>
                <Link to="/memo/new-memo" className="addBtnContainer">
                    <button className="addMemoBtn"> Add Memo </button>
                </Link>
            </div>
        )
    }
    
}


function MainPage() {

    const baseurl = "http://localhost:9000/memo";

    const [memoList, setMemoList] = useState([]);
    useEffect(() => {
        getMemos();
    },[]);

    async function getMemos() {
        await axios.get(baseurl)
        .then((response) => {
        console.log(response.data)
        setMemoList(response.data.result);
        })
    }
    
    return(
        <PageFrame view={MainDetail(memoList)}/>
    )
}

export default MainPage;