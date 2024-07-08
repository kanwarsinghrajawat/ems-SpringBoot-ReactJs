import { Link } from "react-router-dom";

const SignUp = () => {
  return (
    <div className="mx-auto my-10 max-w-md rounded-xl border px-4 py-10 text-gray-700 shadow-lg sm:px-8">
      <div className="mb-16 flex justify-between">
        <span className="font-bold">
          <span className="inline-block h-3 w-3 bg-blue-600"></span> ems
        </span>
        <span className="">
          Have account?{" "}
          <Link to="/" className="font-medium text-blue-600 hover:underline">
            Log in
          </Link>
        </span>
      </div>
      <p className="mb-5 text-3xl font-medium">Manage your business with us!</p>

      <div className="mb-6">
        <div className="focus-within:border-b-blue-500 relative mb-3 flex overflow-hidden border-b-2 transition">
          <input
            type="email"
            id="email"
            className="w-full flex-1 appearance-none border-blue-300 bg-white px-4 py-2 text-base text-gray-700 placeholder-gray-400 focus:outline-none"
            placeholder="Email"
          />
        </div>
        <div className="focus-within:border-b-blue-500 relative mb-3 flex overflow-hidden border-b-2 transition">
          <input
            type="password"
            id="password"
            className="w-full flex-1 appearance-none border-blue-300 bg-white px-4 py-2 text-base text-gray-700 placeholder-gray-400 focus:outline-none"
            placeholder="Password"
          />
        </div>
      </div>
      <button className="mb-6 rounded-xl bg-blue-600 px-8 py-3 font-medium text-white hover:bg-blue-700">
        Get Started
      </button>
      <p className="">
        By signing up you are agreeing to our{" "}
        <a
          href="#"
          className="whitespace-nowrap font-medium text-gray-900 hover:underline"
        >
          Terms and Conditions
        </a>
      </p>
    </div>
  );
};

export default SignUp;
