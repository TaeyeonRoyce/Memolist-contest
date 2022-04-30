import React from 'react';
import PageFrame from './PageFrame';
import { Link, useLocation } from 'react-router-dom';

function MemoDetail(memo) {
    return (
        <div className="memoDetailContainer">
            <h1>{memo.memoTitle}</h1>
            <h3>{memo.memoDetail}</h3>    
            <div className="btnContainer">
                <Link to={{
                    pathname : `/memo/${memo.memoIdx}/edit`}} state = {{memo:memo}}>
                    <button className="editBtn">
                        Edit
                    </button>
                </Link>
                
                <Link to="/memo">
                    <button className="backwardBtn">
                        Backward
                    </button>
                </Link>
               
            </div>
        </div>
    )
}

function MemoDetailPage() {
    const memo = useLocation().state.memo;
    return(
        <PageFrame view={MemoDetail(memo)}/>
    )
}

export default MemoDetailPage;