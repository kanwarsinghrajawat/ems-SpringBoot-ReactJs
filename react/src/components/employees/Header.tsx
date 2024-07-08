import { useDispatch } from "react-redux";
import { MdOutlineAddCircle } from "react-icons/md";
import { setmodalHandler } from "../../redux/slices/employeeSlice";
import { RiLoginCircleLine } from "react-icons/ri";
import { RiLogoutCircleLine } from "react-icons/ri";
import { Link } from "react-router-dom";

const Header = () => {
  const dispatch = useDispatch();
  return (
    <>
      <div className="flex justify-between">
        <p className="text-black text-2xl font-bold">Employees </p>
        <div className="flex justify-center space-x-4 py-4">
          <button
            onClick={() => {
              dispatch(setmodalHandler(true));
            }}
            className="bg-[#EB5017] text-white flex items-center gap-2 rounded-md p-3 hover:bg-[#d04915] transition-colors duration-300"
          >
            <MdOutlineAddCircle size={20} />
            <span className="hidden sm:inline">Add More Records</span>
          </button>
          <Link
            to="/"
            className="flex items-center justify-center bg-gray-200 rounded-full p-2 hover:bg-gray-300 transition-colors duration-300 h-12"
          >
            <RiLogoutCircleLine size={20} />
          </Link>
        </div>
      </div>
    </>
  );
};
export default Header;

{
  /* <RiLoginCircleLine />  */
}
