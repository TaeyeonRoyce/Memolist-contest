import React, {useState} from 'react';
import PageFrame from './PageFrame';
import { Link, useLocation } from 'react-router-dom';
import SecretMemoEnrtyPage from './SecretMemoEntryPage';

function MemoDetail(memo) {
    const [pass, setPass] = useState(false);
    const secret = memo.secret;

    return (
        <div className="memoDetailContainer">
            {!pass && secret ? (<SecretMemoEnrtyPage memo = {memo} setPass={setPass}/>) : (
            <div>
                <div className="memoTitleForm">
                    <div className="titleFormText">Title : </div>
                    <h1>{memo.memoTitle}</h1>
                </div>
                <div className="memoDetailViewContainer">
                    <div className="memoDetailForm">
                        <div className="detailFormText">Memo detail : </div>
                        <h3>{memo.memoDetail}</h3>    
                    </div>
                </div>
                <div className="buttonContainer">
                    <Link to={{
                        pathname : `/memo/${memo.memoIdx}/edit`}} state = {{memo:memo}}>
                        <button className="editBtn">
                            Edit
                        </button>
                    </Link>
                    
                    <Link to="/memo">
                        <button className="return">
                            Backward
                        </button>
                    </Link>
                </div>
            </div>
            )}
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