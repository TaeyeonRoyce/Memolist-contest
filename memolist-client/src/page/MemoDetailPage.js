import React from 'react';
import PageFrame from './PageFrame';

function MemoDetail({memo}) {
    return (
        <div className="memoDetailContainer">
            <h1>{memo.memoTitle}</h1>
            <h3>{memo.memoDetail}</h3>    
        </div>
        
    )
}

function MemoDetailPage(props) {
    const memo = props.location.state;
    // const baseurl = "localhost:9000/memo";
    // const { match } = this.props;
    // let memoIdx = match.params.memoIdx;

    // const [memo, setSingleMemo] = useState();
    // useEffect(() => {
    //     getSingleMemos();
    // },[]);

    // async function getSingleMemos() {
    //     await axios.get(baseurl + "/" + memoIdx)
    //     .then((response) => {
    //     console.log(response.data)
    //     setSingleMemo(response.data.result);
    //     })
    // }

    return(
        <PageFrame view={MemoDetail(memo)}/>
    )

}

export default MemoDetailPage;