import LocalDate from './LocalDate';
import '../App.css';

function PageFrame(props) {
    return(
    <div className="container">
        <div className="contentContainer">
            <LocalDate/>
            {props.view}
        </div>
    </div>
    )
}

export default PageFrame;