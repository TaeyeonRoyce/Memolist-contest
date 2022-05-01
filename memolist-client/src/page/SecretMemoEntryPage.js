import React, { useState } from 'react';
import PageFrame from './PageFrame';
import { Link, useLocation } from 'react-router-dom';
import axios from 'axios';

function SecretMemoEntry({memo, setPass}) {
  const [wrong, setWrong] = useState(false);
  const [pwdHolder, setPwdHolder] = useState("Enter Password");
  const [pwd, setPwd] = useState("");

  function checkPwd(e) {
    const baseUrl = "http://localhost:9000/memo"
    const memoPwd = {
      pwd : pwd
    }

    const checkPwd = async () =>{
    await axios
      .post(baseUrl + "/" + memo.memoIdx + "/auth", JSON.stringify(memoPwd),
      {headers: {"Content-Type": `application/json`,}})
      .then((response) => {
        console.log(response.data);
        if (response.data.result === true) {
          setPass(true);
        } else {
          setPwdHolder("Wrong Password!")
          setWrong(true);
        }
      })
    }
    checkPwd();
  }

  function changeMemoPwd(e) {
    e.preventDefault();
    setPwd(e.target.value);
  }

  return (
    <div className="memoPwdEnterContainer">
      <div className="memoPwd">
        <input type="text" onChange={changeMemoPwd} name="memoPwd" placeholder={pwdHolder}/>
      </div>
      <div className="buttonContainer">
        <button type="submit" className="enterBtn" onClick={checkPwd}> Enter </button>
        <Link to="/memo">
          <button type="button" className="return"> Backward </button>
        </Link>
      </div>
    </div>
  )
}

export default SecretMemoEntry;