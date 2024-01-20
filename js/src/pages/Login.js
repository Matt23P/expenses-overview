import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { Button, Link, Spinner } from '@nextui-org/react'
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { EoUser } from '../utils/EoUser';
import { ensureNotAuthenticated } from '../utils/Helpers';
import '../styles/Login.css';


function Login() {
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [pwd, setPwd] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [errMsg, setErrMsg] = useState('');
    const [loginSuccess, setLoginSuccess] = useState(false);

    useEffect(() => {
        ensureNotAuthenticated(navigate);
    }, [])

    useEffect(() => {
        if (errMsg) {
            const timeoutId = setTimeout(() => {
                setErrMsg('');
            }, 5000);

            // Wyczyszczenie timeoutu w przypadku odmontowania komponentu
            return () => clearTimeout(timeoutId);
        }
    }, [errMsg]);

    const handleLogin = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);

        const request = {
            email: email,
            password: pwd,
        };

        const response = null;

        if (response !== null) {
            if (response.status) {

                setLoginSuccess(true);
                setTimeout(() => {
                    navigate('/');
                }, 5000)
            } else {
                setErrMsg(response.error);
            }
        } else {
            setErrMsg("Please try again later.");
            console.log("Login failed - service unavailable")
        }

        setTimeout(() => {
            setErrMsg('');
        }, 5000)

        setIsSubmitting(false);
    }

    return (
        <>
            <div className='flex flex-col justify-center items-center mt-20'>
                {loginSuccess ? (
                    <>
                        <div className='rounded-3xl border bg-gray-200 w-1/4 h-auto' style={{ boxShadow: "12px 12px 30px 5px rgba(62,66,66,0.35)" }}>
                            <div className='flex flex-col justify-center items-center mt-3'>
                                <div className='text-gray-800 text-3xl text-center font-semibold'>
                                    Login successful!
                                </div>
                                <div className='text-gray-800 text-lg text-center mt-3 mb-3'>
                                    You will be redirected to home page in few seconds.
                                </div>

                            </div>
                        </div>

                        <div className='mt-12'>
                            <div class="spinner">
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                            </div>
                        </div>
                    </>
                ) : (
                    <>
                        <div className='rounded-3xl border bg-gray-200 w-1/4 h-auto' style={{ boxShadow: "12px 12px 30px 5px rgba(62,66,66,0.35)" }}>
                            <section>
                                <form className='flex flex-col justify-center items-center' onSubmit={handleLogin}>
                                    <div className='text-gray-800 font-bold text-3xl mt-3 mb-3'>Login</div>
                                    <FontAwesomeIcon icon={faUserCircle} className='text-gray-800 text-5xl mt-2 mb-2' />
                                    <label htmlFor='email' className='mt-3'>
                                        Email:
                                    </label>
                                    <input
                                        type='email'
                                        id='email'
                                        autoComplete='email'
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        required
                                        className='rounded-lg'
                                    />
                                    <label htmlFor='password' className='mt-3'>
                                        Password:
                                    </label>
                                    <input
                                        type='password'
                                        id='password'
                                        autoComplete='current-password'
                                        value={pwd}
                                        onChange={(e) => setPwd(e.target.value)}
                                        required
                                        className='rounded-lg'
                                    />
                                    <Button
                                        disabled={!email || !pwd || isSubmitting}
                                        type='submit'
                                        variant='solid'
                                        className='mt-5 mb-5 cursor-pointer'
                                    >
                                        Login
                                    </Button>

                                </form>
                            </section>
                        </div>
                        <div className='rounded-3xl border bg-gray-200 w-1/4 h-auto mt-8' style={{ boxShadow: "12px 12px 30px 5px rgba(62,66,66,0.35)" }}>
                            <div className='text-gray-800 text-base flex justify-center text-center font-semibold mt-2 mb-2'>
                                <Link color='foreground' href='/signup'>
                                    Click here to create an account
                                </Link>
                            </div>
                        </div>
                        <div className={errMsg ? 'flex flex-col justify-center items-center rounded-3xl border bg-red-300 w-1/4 h-auto mt-8' : 'hidden'}>
                            {errMsg && (
                                <div>
                                    {errMsg}
                                </div>
                            )}
                        </div>
                    </>
                )}
            </div>
        </>
    );
}

export default Login;