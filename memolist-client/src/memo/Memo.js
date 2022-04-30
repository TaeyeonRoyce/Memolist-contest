import React from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";
import MemoPreview from './MemoPreview';

function Memo({key, memo}) {

    const baseUrl = "http://localhost:9000/memo";

    function deleteTodo(e) {
        e.preventDefault();

        const deleteTodo = async () =>{
        await axios
            .delete(baseUrl + "" + memo.memoIdx);
        }
        deleteTodo();
        console.log("삭제완료");
    }
    return(
        <div className="memoCotainer" key={key}>
            <Link to={{
                    pathname : `/memo/${memo.memoIdx}`,
                    state : {memo}}}>
                <MemoPreview memo = {memo} secret= {memo.secret}/>
            </Link>
            <form className="deleteTodo" onSubmit={deleteTodo}>
                <button type="submit"> ❌ </button>
            </form>
        </div>
    )
}

export default Memo;