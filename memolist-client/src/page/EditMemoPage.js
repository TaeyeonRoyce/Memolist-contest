import React, {useState} from 'react';
import PageFrame from './PageFrame';
import axios from 'axios';
import { Link, useLocation } from 'react-router-dom';


function EditMemo(memo) {
  const [added, setAdded] = useState(false);
  
  const [memoTitle, setMemoTitle] = useState("");
  const [memoDetail, setMemoDetail] = useState("");
  
  const [secret, setSecret] = useState(memo.secret);
  const [pwd, setPwd] = useState("");

  function editNormalTodo(e) {
    console.log("Normal Memo Add func");

    const baseUrl = "http://localhost:9000/memo"

    e.preventDefault();

    const memoPost = {
      memoTitle : memoTitle,
      memoDetail : memoDetail
    }

    const editNormalMemoDetail = async () =>{
    await axios
      .patch(baseUrl + "/" + memo.memoIdx, JSON.stringify(memoPost),
      {headers: {"Content-Type": `application/json`,}})
      .then((response) => {
        console.log(response.data);
        setMemoTitle("");
        setMemoDetail("");
        setSecret(false);
        setPwd("");
        setAdded(true);
      })
    }
    editNormalMemoDetail();
    
    console.log("Normal Memo Added");
  }

  function editSecretMemo(e) {
    console.log("Secret Memo Add func");

    const baseUrl = "http://localhost:9000/memo"

    e.preventDefault();

    const memoPost = {
      memoTitle : memoTitle,
      memoDetail : memoDetail,
      secretLevel : "HIGH",
      memoPwd : pwd
    }

    const editSecretMemoDetail = async () =>{
    await axios
      .patch(baseUrl + "/" + memo.memoIdx + "/secret", JSON.stringify(memoPost),
      {headers: {"Content-Type": `application/json`,}})
      .then((response) => {
        console.log(response.data);
        setMemoTitle("");
        setMemoDetail("");
        setSecret(false);
        setPwd("");
        setAdded(true);
      })
    }
    editSecretMemoDetail();
  }

    function checkSecretBox(e) {
      setSecret(!secret);
    }

    function changeMemoTitle(e) {
      e.preventDefault();
      setMemoTitle(e.target.value);
    }
    function changeMemoDetail(e) {
      e.preventDefault();
      setMemoDetail(e.target.value);
    }

    function changeMemoPwd(e) {
      e.preventDefault();
      setPwd(e.target.value);
    }

    return (
      <div className="addFormContainer">
        <div className="messageContainer">
            {added ? 
              (<div className="finishedMessage"> Memo Edit Success! </div>) :
              (<div className="addMessage"> Create New Memo!</div>)
            }
        </div>
        <form action="todos.html" method="post" onSubmit={secret ? editSecretMemo : editNormalTodo}>
          <div className="secretCheckBox">
            <input type="checkbox" checked={secret}  onChange={(e) => checkSecretBox(e)} />
            <div>Secret Memo</div>
          </div>
          <div className="memoTitleForm">
            <input type="text" onChange={changeMemoTitle} name="memoTitle" placeholder={memo.memoTitle}/>
          </div>
          <div className="memoDetailForm">
            <input type="text" onChange={changeMemoDetail} name="memoDetail" placeholder={memo.memoDetail}/>
          </div>
          {secret ? (
          <div className="memoPwd">
            <input type="text" onChange={changeMemoPwd} name="memoPwd" placeholder={memo.memoPwd}/>
          </div>) : (<div></div>)}
          <div className="buttonContainer">
            {added ? (
              <div></div>
            ) : (<button type="submit" className="addTodo"> Edit </button>)}
            <Link to="/memo">
                <button type="button" className="return"> Backward </button>
            </Link>
          </div>
        </form>
      </div>
    )
}

function EditMemoPage() {
  const memo = useLocation().state.memo;
  return(
    <PageFrame view={EditMemo(memo)}/>
  )
}

export default EditMemoPage;