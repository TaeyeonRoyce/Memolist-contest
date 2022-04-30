import React from 'react';

function MemoPreview({memo, secret}) {
  return(
    <div>
      {secret ? (
        <div className="secretMemoPreview">
          <h3>Secret Memo</h3>
        </div>
      ) : (
          <div className="normalMemo">
            <h3>Normal Memo</h3>
            <div className="memoTitle">
              {memo.memoTitle}
            </div>
            <div className="todosDetail">
              {memo.memoDetail}
            </div>
          </div>
        )
      }
    </div>
  )
}

export default MemoPreview;