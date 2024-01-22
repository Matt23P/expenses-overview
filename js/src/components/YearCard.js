import React from 'react'

function YearCard(props) {
    const year = props.year;

    return (
        <div className='bg-gray-200 flex justify-center items-center w-24 h-24 rounded-2xl cursor-pointer transition duration-300 ease-in-out transform hover:bg-gray-900 hover:text-white' style={{ boxShadow: "8px 8px 10px 5px rgba(62,66,66,0.35)" }}>
            <div className='font-semibold text-3xl'>
                {year}
            </div>
        </div>
    );
}

export default YearCard;