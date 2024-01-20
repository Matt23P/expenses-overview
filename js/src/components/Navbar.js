import React from 'react'


function Navbar() {
    return (
        <>
            <div className='bg-gray-400 w-full h-auto' style={{ boxShadow: "15px 15px 40px 10px rgba(62,66,66,0.35)" }}>
                <div className='flex justify-center items-center'>
                    <div className='mt-3 mb-3'>
                        <div className='text-gray-800 font-semibold text-4xl '>
                            Expenses Overview
                        </div>

                    </div>

                </div>
            </div>
        </>
    );
}

export default Navbar;