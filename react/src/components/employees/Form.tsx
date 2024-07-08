import React, { useEffect, useState } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import { Employee, Gender, Role } from ".";
import { useCallback } from "react";
import { useDispatch } from "react-redux";
import {
  employeeIdUpdate,
  setmodalHandler,
} from "../../redux/slices/employeeSlice";
import { useSelector } from "react-redux";
import axios from "axios";
import { RootState } from "../../redux/store";
const Form = ({}) => {
  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors },
  } = useForm<Employee>();
  const dispatch = useDispatch();
  const [employeeError, setEmployeeError] = useState(false);
  const [showEditData, setShowEditData] = useState(false);
  const [formState, setFormState] = useState<Employee>({
    id: 0,
    name: "",
    age: "0",
    role: "" as unknown as Role,
    dob: "",
    gender: "" as unknown as Gender,
    email: "",
    password: "",
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormState((prevState) => ({
      ...prevState,
      [name]: value,
    }));
    setValue(name as keyof Employee, value);
  };

  const employees = useSelector((state: RootState) => state.data);
  const employeesData = employees.employees;
  const employeeIdValue = employees.employeeId;
  console.log(employees);
  useEffect(() => {
    if (employees?.employeeId === null) {
      setShowEditData(false);
    } else {
      setShowEditData(true);
      const foundEmployee = findEmployeeById(employeesData, employeeIdValue);
      console.log(foundEmployee);
      if (foundEmployee) {
        setFormState(foundEmployee);
      }
    }
  }, [employees, employeesData, employeeIdValue]);

  useEffect(() => {
    if (showEditData) {
      setValue("id", formState.id);
      setValue("name", formState.name);
      setValue("age", formState.age);
      setValue("dob", formState.dob);
      setValue("gender", formState.gender);
      setValue("role", formState.role);
      setValue("email", formState.email);
      setValue("password", formState.password);
    }
  }, [showEditData, formState, setValue]);

  const findEmployeeById = (
    employeesData: Employee[],
    employeeIdValue: number
  ): Employee | undefined => {
    return employeesData.find((employee) => employee.id === employeeIdValue);
  };

  const handleUpdateSubmit = async (data: any, id: number) => {
    try {
      await axios.put(`http://localhost:9091/api/employees/${id}`, data);
    } catch (error) {}
  };

  const onSubmit: SubmitHandler<Employee> = useCallback(
    (data) => {
      const employeeExists = employeesData.some(
        (employee) => employee.id == data.id
      );

      if (showEditData) {
        handleUpdateSubmit(data, data.id);

        dispatch(setmodalHandler(false));
      } else {
        if (employeeExists) {
          setEmployeeError(true);
        } else {
          (async () => {
            try {
              const response = await axios.post(
                "http://localhost:9091/api/employees",
                data,
                { headers: { "Content-Type": "application/json" } }
              );

              console.log(response.data);
            } catch (error) {
              console.error("Error making the API call", error);
            }
          })();

          dispatch(setmodalHandler(false));
        }
      }

      dispatch(employeeIdUpdate(null));
    },
    [dispatch, employeesData, showEditData]
  );

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="fixed inset-0 flex w-screen items-center justify-center p-4 bg-gray-800 bg-opacity-75"
    >
      <div className="bg-white p-8 rounded-lg shadow-lg max-w-lg w-full">
        <input
          {...register("name", {
            required: "Name is required",
          })}
          onChange={handleInputChange}
          className="mt-6 px-3 py-2 border border-gray-300 rounded-md w-full focus:outline-none focus:border-blue-500"
          placeholder="Name"
        />
        {errors.name && (
          <span className="text-red-500">{errors.name.message}</span>
        )}

        <input
          type="number"
          {...register("age", {
            required: "Age is required",
            valueAsNumber: true,
            min: {
              value: 18,
              message: "You must be at least 18 years old.",
            },
          })}
          onChange={handleInputChange}
          className="mt-6 px-3 py-2 border border-gray-300 rounded-md w-full focus:outline-none focus:border-blue-500"
          placeholder="Age"
        />
        {errors.age && (
          <span className="text-red-500">{errors.age.message}</span>
        )}
        <input
          type="date"
          {...register("dob", {
            required: "Dob is required",
          })}
          onChange={handleInputChange}
          className=" mt-6 px-3 py-2 border cursor-pointer border-gray-300 rounded-md w-full focus:outline-none focus:border-blue-500"
          placeholder="Date Of Birth"
        />
        {errors.dob && (
          <span className="text-red-500">{errors.dob.message}</span>
        )}
        <input
          type="email"
          {...register("email", {
            required: "email is required",
          })}
          onChange={handleInputChange}
          className=" mt-6 px-3 py-2 border cursor-pointer border-gray-300 rounded-md w-full focus:outline-none focus:border-blue-500"
          placeholder="Email"
        />
        {errors.email && (
          <span className="text-red-500">{errors.email.message}</span>
        )}
        <input
          type="password"
          {...register("password", {
            required: "Password is required",
          })}
          onChange={handleInputChange}
          className=" mt-6 px-3 py-2 border cursor-pointer border-gray-300 rounded-md w-full focus:outline-none focus:border-blue-500"
          placeholder="Password"
        />
        {errors.password && (
          <span className="text-red-500">{errors.password.message}</span>
        )}

        <div className="mt-6">
          <label className="block mb-2">Gender</label>
          <div className="flex items-center">
            <input
              type="radio"
              {...register("gender", {
                required: "Gender is required",
              })}
              onChange={handleInputChange}
              value={Gender.MALE}
              checked={formState.gender === Gender.MALE}
              className="mr-2"
            />
            <label className="mr-6">Male</label>

            <input
              type="radio"
              {...register("gender", {
                required: "Gender is required",
              })}
              onChange={handleInputChange}
              value={Gender.FEMALE}
              checked={formState.gender === Gender.FEMALE}
              className="mr-2"
            />
            <label className="mr-6">Female</label>

            <input
              type="radio"
              {...register("gender", {
                required: "Gender is required",
              })}
              onChange={handleInputChange}
              value={Gender.OTHER}
              checked={formState.gender === Gender.OTHER}
              className="mr-2"
            />
            <label>Other</label>
          </div>
          {errors.gender && (
            <span className="text-red-500">{errors.gender.message}</span>
          )}
        </div>

        <select
          {...register("role", { required: "Role is required" })}
          className="mt-6 px-3 py-2 border border-gray-300 rounded-md w-full focus:outline-none focus:border-blue-500"
        >
          <option value="">Select Role</option>
          <option value={Role.ADMIN}>{Role.ADMIN}</option>
          <option value={Role.GUEST}>{Role.GUEST}</option>
          <option value={Role.USER}>{Role.USER}</option>
        </select>
        {errors.role && (
          <span className="text-red-500">{errors.role.message}</span>
        )}

        <div className="flex mt-4 justify-end gap-4">
          <button
            type="submit"
            className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition duration-300"
          >
            Submit
          </button>
          <button
            onClick={() => {
              dispatch(setmodalHandler(false));
              dispatch(employeeIdUpdate(null));
            }}
            className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition duration-300"
          >
            Close
          </button>
        </div>
      </div>
    </form>
  );
};

export default Form;
