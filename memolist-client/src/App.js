import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
import AddMemoPage from './page/AddMemoPage';
import EditMemoPage from './page/EditMemoPage';
import MemoDetailPage from './page/MemoDetailPage';
import MemoListPage from "./page/MemoListPage"


function App() {
  return (
    <Router>
      <Routes>
        <Route exact path='/memo' element={<MemoListPage/>} />
        <Route exact path='/memo/new-memo' element={<AddMemoPage/>} />
        <Route exact path='/memo/:memoIdx' element={<MemoDetailPage/>} />
        <Route exact path='/memo/:memoIdx/edit' element={<EditMemoPage/>} />
      </Routes>
    </Router>
  );
}

export default App;
