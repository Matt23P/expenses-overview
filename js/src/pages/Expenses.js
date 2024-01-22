import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ensureAuthenticated } from '../utils/Helpers';
import { EoUser } from '../utils/EoUser';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Divider, Chip, Input, Autocomplete, AutocompleteItem } from "@nextui-org/react";
import { Table, TableHeader, TableColumn, TableBody, TableRow, TableCell } from "@nextui-org/react";
import YearCard from '../components/YearCard';
import DeleteButton from '../components/DeleteButton';
import { generalTypes, expenseTypes } from '../utils/Constants';


function Expenses() {

    const expenses = [
        {
            amount: 12.30,
            description: "",
            type: "EATOUT",
            income: false,
            currency: "PLN",
        },
        {
            amount: 500.00,
            description: "Help from Dad",
            type: "FUN",
            income: true,
            currency: "PLN",
        },
        {
            amount: 120.78,
            description: "Groceries",
            type: "GROCERIES",
            income: false,
            currency: "PLN",
        },
        {
            amount: 360.00,
            description: "Studies - monthly fee",
            type: "EDUCATION",
            income: false,
            currency: "PLN",
        },
    ]

    const years = ["2022", "2023", "2024"];

    const months = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]

    const days = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"]

    const monthLength = 31;

    const choosedYear = 2023;

    const choosedMonth = 7;

    const choosedDay = 13;

    const userID = EoUser.id;
    const userCurrency = EoUser.userCurrency;

    const navigate = useNavigate();

    useEffect(() => {
        ensureAuthenticated(navigate);
    }, []);

    return (
        <>

            {/* YEAR SELECT */}
            <div className='flex flex-col mt-8'>
                <div className='flex flex-row justify-between'>
                    <div className='flex ml-8 mb-8 justify-start items-start text-4xl font-semibold'>
                        Year
                    </div>
                    <div className='flex mr-8 mb-8 justify-end items-end text-4xl font-semibold'>
                        <DeleteButton />
                    </div>
                </div>

                <div className='grid gap-6 justify-center' style={{ display: 'flex', flexWrap: 'wrap' }}>
                    {years.map((year, index) => (
                        <YearCard key={index} year={year} style={{ flex: '0 0 0%', boxSizing: 'border-box', padding: '8px' }} />
                    ))}
                    <div className='bg-gray-200 flex justify-center items-center w-24 h-24 rounded-2xl cursor-pointer text-4xl text-green-600 transition duration-300 ease-in-out transform hover:bg-green-600 hover:text-white' style={{ boxShadow: "8px 8px 10px 5px rgba(62,66,66,0.35)" }}>
                        <FontAwesomeIcon icon={faPlus} />
                    </div>
                </div>
                <div className='flex justify-center items-center mt-8'>
                    <Divider orientation='horizontal' className='w-5/6' />
                </div>
            </div>


            {/* MONTH SELECT */}
            <div className='flex flex-col mt-8'>
                <div className='flex flex-row justify-between'>
                    <div className='flex ml-8 mb-8 justify-start items-start text-4xl font-semibold'>
                        Month
                    </div>
                    <div className='flex mr-8 mb-8 justify-end items-end text-4xl font-semibold'>
                        <DeleteButton />
                    </div>
                </div>
                <div className='grid gap-6 justify-center' style={{ display: 'flex', flexWrap: 'wrap' }}>
                    {months.map((month, index) => (
                        <YearCard key={index} year={month} style={{ flex: '0 0 0%', boxSizing: 'border-box', padding: '8px' }} />
                    ))}
                    {months.length === 12 ? (
                        <>
                        </>
                    ) : (
                        // transition duration-300 ease-in-out transform hover:bg-gray-900 hover:text-white
                        <div className='bg-gray-200 flex justify-center items-center w-24 h-24 rounded-2xl cursor-pointer text-4xl text-green-600 transition duration-300 ease-in-out transform hover:bg-green-600 hover:text-white' style={{ boxShadow: "8px 8px 10px 5px rgba(62,66,66,0.35)" }}>
                            <FontAwesomeIcon icon={faPlus} />
                        </div>
                    )}
                </div>
                <div className='flex justify-center items-center mt-8'>
                    <Divider orientation='horizontal' className='w-5/6' />
                </div>
            </div>


            {/* DAY SELECT */}
            <div className='flex flex-col mt-8'>
                <div className='flex flex-row justify-between'>
                    <div className='flex ml-8 mb-8 justify-start items-start text-4xl font-semibold'>
                        Day
                    </div>
                    <div className='flex mr-8 mb-8 justify-end items-end text-4xl font-semibold'>
                        <DeleteButton />
                    </div>
                </div>
                <div className='grid gap-6 justify-center' style={{ display: 'flex', flexWrap: 'wrap' }}>
                    {days.map((day, index) => (
                        <YearCard key={index} year={day} style={{ flex: '0 0 0%', boxSizing: 'border-box', padding: '8px' }} />
                    ))}
                    {days.length === monthLength ? (
                        <>
                        </>
                    ) : (
                        <div className='bg-gray-200 flex justify-center items-center w-24 h-24 rounded-2xl cursor-pointer text-4xl text-green-600 transition duration-300 ease-in-out transform hover:bg-green-600 hover:text-white' style={{ boxShadow: "8px 8px 10px 5px rgba(62,66,66,0.35)" }}>
                            <FontAwesomeIcon icon={faPlus} />
                        </div>
                    )}
                </div>
                <div className='flex justify-center items-center mt-8'>
                    <Divider orientation='horizontal' className='w-5/6' />
                </div>
            </div>


            {/* EXPENSES SECTION */}
            <div className='flex flex-col mt-8'>
                <div className='flex flex-row justify-between'>
                    <div className='flex ml-8 mb-8 justify-start items-start text-4xl font-semibold'>
                        Expenses on {choosedDay}.{choosedMonth}.{choosedYear}
                    </div>
                </div>

                <div className='flex justify-center items-center'>
                    <Table removeWrapper aria-label="Example static collection table" className='w-5/6' style={{ boxShadow: "8px 8px 20px 15px rgba(62,66,66,0.35)" }}>
                        <TableHeader>
                            <TableColumn>Income /expense</TableColumn>
                            <TableColumn>Amount</TableColumn>
                            <TableColumn>Description</TableColumn>
                            <TableColumn>Type</TableColumn>
                            <TableColumn>Action</TableColumn>
                        </TableHeader>
                        <TableBody>
                            {expenses.map((expense, index) =>
                                <TableRow key={index}>
                                    {expense.income ? (
                                        <TableCell className=''>
                                            <Chip variant="flat" color='success'>
                                                Income
                                            </Chip>
                                        </TableCell>
                                    ) : (
                                        <TableCell className=''>
                                            <Chip variant="flat" color='danger'>
                                                Expense
                                            </Chip>
                                        </TableCell>
                                    )}
                                    <TableCell className={expense.income ? "text-green-700" : "text-red-500"}>
                                        {expense.amount} {expense.currency}
                                    </TableCell>
                                    <TableCell>{expense.description}</TableCell>
                                    <TableCell>{expense.type}</TableCell>
                                    <TableCell className='flex justify-center items-center'>
                                        <FontAwesomeIcon icon={faTrashCan} className='text-xl text-red-500 my-3 cursor-pointer' />
                                    </TableCell>
                                </TableRow>
                            )}
                            <TableRow>
                                <TableCell>
                                    <Autocomplete
                                        isRequired
                                        label="Type"
                                        defaultItems={generalTypes}
                                        placeholder="Search"
                                        // defaultSelectedKey="cat"
                                        className="max-w-xs"
                                    >
                                        {(item) => <AutocompleteItem key={item.value}>{item.label}</AutocompleteItem>}
                                    </Autocomplete>
                                </TableCell>
                                <TableCell>
                                    <Input
                                        isRequired
                                        type="number"
                                        placeholder="0.00"
                                        className="max-w-xs"
                                        startContent={
                                            <div className="pointer-events-none flex items-center">
                                                <span className="text-default-400 text-small">{userCurrency}</span>
                                            </div>
                                        }
                                    />
                                </TableCell>
                                <TableCell>
                                    <Input type='text' variant={'flat'} placeholder="Description" />
                                </TableCell>
                                <TableCell>
                                    <Autocomplete
                                        isRequired
                                        label="Type"
                                        defaultItems={expenseTypes}
                                        placeholder="Search"
                                        // defaultSelectedKey="cat"
                                        className="max-w-xs"
                                    >
                                        {(item) => <AutocompleteItem key={item.value}>{item.label}</AutocompleteItem>}
                                    </Autocomplete>
                                </TableCell>
                                <TableCell className='flex justify-center items-center'>
                                    <FontAwesomeIcon icon={faPlus} className='text-2xl text-green-600 my-3 cursor-pointer' />
                                </TableCell>
                            </TableRow>

                        </TableBody>

                    </Table>
                </div>




                <div className='grid gap-6 justify-center' style={{ display: 'flex', flexWrap: 'wrap' }}>

                </div>
                <div className='flex justify-center items-center mt-8'>
                    <Divider orientation='horizontal' className='w-5/6' />
                </div>
            </div >
        </>
    );
}

export default Expenses;