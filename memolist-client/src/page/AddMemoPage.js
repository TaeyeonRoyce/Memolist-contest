import React, {useState} from 'react';
import PageFrame from './PageFrame';
import axios from 'axios';
import { Link } from 'react-router-dom';


function AddMemo() {
  const [added, setAdded] = useState(false);
  
  const [memoTitle, setMemoTitle] = useState("");
  const [memoDetail, setMemoDetail] = useState("");
  
  const [secret, setSecret] = useState(false);
  const [pwd, setPwd] = useState("");

  function addNormalMemo(e) {
    console.log("Normal Memo Add func");

    const baseUrl = "http://localhost:9000/memo"

    e.preventDefault();

    const memoPost = {
      memoTitle : memoTitle,
      memoDetail : memoDetail
    }

    const addNormalMemoDetail = async () =>{
    await axios
      .post(baseUrl + "/", JSON.stringify(memoPost),
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
    addNormalMemoDetail();
    
    console.log("Normal Memo Added");
  }

  function addSecretMemo(e) {
    console.log("Secret Memo Add func");

    const baseUrl = "http://localhost:9000/memo"

    e.preventDefault();

    const memoPost = {
      memoTitle : memoTitle,
      memoDetail : memoDetail,
      secretLevel : "HIGH",
      memoPwd : pwd
    }

    const addSecretMemoDetail = async () =>{
    await axios
      .post(baseUrl + "/secret", JSON.stringify(memoPost),
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
    addSecretMemoDetail();
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
              (<div className="finishedMessage"> Add Memo Success! </div>) :
              (<div className="addMessage"> Create New Memo!</div>)
            }
        </div>
        <form action="" method="post" onSubmit={secret ? addSecretMemo : addNormalMemo}>
          <div className="secretCheckBox">
            <input type="checkbox" checked={secret}  onChange={(e) => checkSecretBox(e)} />
            <div>Secret Memo</div>
          </div>
          <div className="addFormInputContainer">
            <div className="memoTitleForm">
              <div className="titleFormText">Title : </div>
              <input type="text" onChange={changeMemoTitle} name="memoTitle" placeholder="Memo Title"/>
            </div>
            <div className="memoDetailForm">
              <div className="detailFormText">Memo detail : </div>
              <textarea type="text" onChange={changeMemoDetail} name="memoDetail" placeholder="..."/>
            </div>
            {secret ? (
            <div className="memoPwd">
              <div className="pwdFormText">Memo password :  </div>
              <input type="text" onChange={changeMemoPwd} name="memoPwd" placeholder="password"/>
            </div>) : (<div></div>)}
          </div>
          <div className="buttonContainer">
            {added ? (
              <div className="noneDiv"></div>
            ) : ( <button type="submit" className="addMemoSubmitBtn"> Add Memo </button> )}
            <Link to="/memo">
                <button type="button" className="return"> Backward </button>
            </Link>
          </div>
        </form>
      </div>
    )
}

function AddMemoPage() {
  return(
    <PageFrame view={AddMemo()}/>
  )
}

export default AddMemoPage;