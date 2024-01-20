import React from 'react';
import { Button, Link } from '@nextui-org/react';
import { faCircleQuestion } from '@fortawesome/free-regular-svg-icons';
import { faMagnifyingGlassDollar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { EoUser } from '../utils/EoUser';

function Home() {
    return (
        <>
            <div className='flex flex-col justify-center items-center mt-16 mb-5'>
                <div className='flex flex-col text-3xl text-center text-gray-800 font-semibold'>
                    <div className='mb-1'>Welcome to Expenses Overview!</div>
                    <div>Easily track your spendings in one place.</div>
                </div>
                <div className='flex flex-col text-xl text-center text-gray-600 mt-3'>
                    Manage your expenses, view analytics, know on what you're spending your money.
                </div>
            </div>

            <div className='flex justify-center mt-6'>
                <div className='flex flex-row gap-8'>
                    <Button radius='large' variant='ghost' style={{ boxShadow: "12px 12px 30px 5px rgba(62,66,66,0.35)" }}>
                        <Link href={EoUser.isLogged ? '/' : 'login'}
                            color='foreground'
                            className='flex flex-row justify-center items-center'>
                            Get Started <FontAwesomeIcon icon={faMagnifyingGlassDollar} className='text-lg ml-2' />
                        </Link>
                    </Button>
                    <Button radius='large' variant='ghost' style={{ boxShadow: "12px 12px 30px 5px rgba(62,66,66,0.35)" }}>
                        <Link href='/' color='foreground' className='flex flex-row justify-center items-center'>
                            How does it work <FontAwesomeIcon icon={faCircleQuestion} className='text-lg ml-2' />
                        </Link>
                    </Button>
                </div>
            </div>
        </>
    );
}

export default Home;