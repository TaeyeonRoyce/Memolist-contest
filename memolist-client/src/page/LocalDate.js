import React from 'react';


function LocalDate() {
    let now = new Date();
    let todayYear = now.getFullYear();
    let todayMonth = now.getMonth() + 1;
    let todayDate = now.getDate() > 9 ? now.getDate() : '0' + now.getDate();
    return(
    <div className="dateContainer">
        <div className="year"> 
            {todayYear}
        </div>
        <div className="date">
            {todayMonth + '/'+ todayDate}
        </div>
    </div>
    )
}

export default LocalDate;