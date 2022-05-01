import React from 'react';
import { CgNotes, CgLock } from "react-icons/cg";

function MemoPreview({memo, secret}) {
  return(
    <div>
      {secret ? (
        <div className="memoPreview">
          <div className="memoIconContainer">
            <CgLock className="secretIcon"/>
          </div>
          <h3 className="secretMemoText"> Passwrod Required</h3>
        </div>
      ) : (
          <div className="memoPreview">
            <div className="memoIconContainer">
              <CgNotes className="memoIcon"/>
            </div>
            <div className="previewContent">
              <div className="memoTitle">
                Title : {memo.memoTitle}
              </div>
              <div className="memoDetails">
                {memo.memoDetail}
              </div>
            </div>
          </div>
        )
      }
    </div>
  )
}

export default MemoPreview;