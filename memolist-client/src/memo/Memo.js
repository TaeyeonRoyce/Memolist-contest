import React from 'react';
import { Link } from "react-router-dom";
import MemoPreview from './MemoPreview';

function Memo({memo}) {
    return(
        <div className="memoCotainer" key={memo.memoIdx}>
            <Link to={{
                pathname : `/memo/${memo.memoIdx}`}} state = {{memo:memo}}>
                <MemoPreview memo = {memo} secret= {memo.secret}/>
            </Link>
        </div>
    )
}

export default Memo;