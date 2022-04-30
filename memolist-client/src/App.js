import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
import MemoDetailPage from './page/MemoDetailPage';
import MemoListPage from "./page/MemoListPage"


function App() {
  return (
    <Router>
      <Routes>
        <Route path='/memo' element={<MemoListPage/>} />
        <Route path='/memo/:memoIdx' element={<MemoDetailPage/>} />
      </Routes>
    </Router>
  );
}

export default App;
