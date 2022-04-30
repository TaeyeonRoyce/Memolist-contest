import React from "react";
import { Link } from "react-router-dom";
import PageFrame from "./PageFrame";
import axios from 'axios';
import Memo from '../memo/Memo'
import { useEffect, useState } from 'react';


function MainDetail(memoList) {
    return (
        <div className="mainPageContainer">
            <Link to="/memo/new-memo">
                <button className="addMemoBtn"> 새로운 메모 추가하기 </button>
            </Link>
            <div className="memoListContainer">
                {memoList.map((memo) => (
                    <Memo memo={memo} key = {memo.memoIdx}></Memo>
                ))}
            </div>
        </div>
    )
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