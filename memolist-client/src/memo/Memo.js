import React from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";
import MemoPreview from './MemoPreview';

function Memo({memo}) {

    const memoIdx = memo.memoIdx;
    const baseUrl = "http://localhost:9000/memo";

    function deleteTodo(e) {
        e.preventDefault();

        const deleteTodo = async () =>{
        await axios
            .delete(baseUrl + "" + memoIdx);
        }
        deleteTodo();
        console.log("삭제완료");
    }
    return(
        <div className="memoCotainer" key={memoIdx}>
            <Link to={{
                    pathname : `/memo/${memoIdx}`}} state = {{memo:memo}}>
                <MemoPreview memo = {memo} secret= {memo.secret}/>
            </Link>
            <form className="deleteTodo" onSubmit={deleteTodo}>
                <button type="submit"> ❌ </button>
            </form>
        </div>
    )
}

export default Memo;