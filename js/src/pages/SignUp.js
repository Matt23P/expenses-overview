import React, { useEffect, useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Link, Autocomplete, AutocompleteItem } from '@nextui-org/react';
import { ensureNotAuthenticated } from '../utils/Helpers';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { signup } from '../utils/Backend';
import { currencies } from '../utils/Currencies';

const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,32}$/;
const EMAIL_REGEX = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const USERNAME_REGEX = /^[a-zA-Z0-9](?:[a-zA-Z0-9 ]*[a-zA-Z0-9])?$/;


function SignUp() {
    const navigate = useNavigate();

    const isPasswordValid = (val) => PWD_REGEX.test(val);
    const isEmailValid = (val) => EMAIL_REGEX.test(val);
    const isNameValid = (val) => USERNAME_REGEX.test(val);

    const [email, setEmail] = useState('');
    const [validEmail, setValidEmail] = useState(false);
    const [emailFocus, setEmailFocus] = useState(false);

    const [username, setUsername] = useState('');
    const [validUsername, setValidUsername] = useState(false);
    const [usernameFocus, setUsernameFocus] = useState(false);

    const [pwd, setPwd] = useState('');
    const [validPwd, setValidPwd] = useState(false);
    const [pwdFocus, setPwdFocus] = useState(false);

    const [matchPwd, setMatchPwd] = useState('');
    const [validMatch, setValidMatch] = useState(false);
    const [matchFocus, setMatchFocus] = useState(false);

    const [errMsg, setErrMsg] = useState('');
    const [valMsg, setValMsg] = useState('');
    const [signupSuccess, setSignupSuccess] = useState(false);
    const [isSubmitting, setIsSubmitting] = useState(false);

    const [currency, setCurrency] = useState('');

    const onCurrencyChange = (value) => {
        setCurrency(value);
        console.log(value);
    }

    useEffect(() => {
        const result = isEmailValid(email)
        if (result) {
            setValMsg('');
        } else {
            setValMsg('Email does not match example@email.com.')
        }
        setValidEmail(result);
    }, [email])

    useEffect(() => {
        const result = isNameValid(username);
        if (result) {
            setValMsg('');
        } else {
            setValMsg('Username must not be longer than 32 chars, cannot start or end with space and cannot contain any special chars.')
        }
        setValidUsername(result);
    }, [username])

    useEffect(() => {
        const result = isPasswordValid(pwd);
        if (result) {
            setValMsg('');
        } else {
            setValMsg('Password must be 8-32 chars long, must contain at least one small letter, capital letter, number and special char.')
        }
        setValidPwd(result);

    }, [pwd])

    useEffect(() => {
        const match = pwd === matchPwd;
        if (match) {
            setValMsg('');
        } else {
            setValMsg('Passwords do not match.')
        }
        setValidMatch(match);
    }, [matchPwd])

    useEffect(() => {
        ensureNotAuthenticated(navigate);
    }, [])

    useEffect(() => {
        if (errMsg) {
            const timeoutId = setTimeout(() => {
                setErrMsg('');
            }, 5000);

            return () => clearTimeout(timeoutId);
        }
    }, [errMsg]);

    const handleRegister = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);

        const v1 = isEmailValid(email);
        const v2 = isPasswordValid(pwd);
        const v3 = isNameValid(username);
        const v4 = pwd === matchPwd;

        if (!v1) {
            setValMsg('This doesn`t match example@email.com.');
        } else if (!v2) {
            setValMsg('Password must be 8-32 chars long, must contain at least one small letter, capital letter, number and special char.');
        } else if (!v3) {
            setValMsg('Username must not be longer than 32 chars, cannot start or end with space and cannot contain any special chars.');
        } else if (!v4) {
            setValMsg('Passwords do not match.')
        }

        const request = {
            email: email,
            username: username,
            pwd: pwd,
            currency: currency
        }

        const response = await signup(request);
        if (response !== null) {
            if (response.status) {
                setSignupSuccess(true);
            } else {
                setErrMsg(response.error);
            }
        } else {
            setErrMsg('Please try again later.');
            console.log('Register failed - service unavailable.')
        }

        setTimeout(() => {
            setErrMsg('');
        }, 5000)

        setIsSubmitting(false);
    }

    return (
        <>
            <div className='flex flex-col justify-center items-center mt-20'>
                {signupSuccess ? (
                    <>
                        <div className='rounded-3xl border bg-gray-200 w-1/4 h-auto' style={{ boxShadow: "12px 12px 30px 5px rgba(62,66,66,0.35)" }}>
                            <div className='flex flex-col justify-center items-center mt-3'>
                                <div className='text-gray-800 text-3xl text-center font-semibold'>
                                    Account successfuly created!
                                </div>
                                <div className='text-gray-800 text-lg text-center mt-3 mb-3'>
                                    Please login to your new account.
                                </div>
                            </div>
                        </div>

                    </>
                ) : (
                    <>
                        <div className='rounded-3xl border bg-gray-200 w-1/4 h-auto' style={{ boxShadow: "12px 12px 30px 5px rgba(62,66,66,0.35)" }}>
                            <section>
                                <form className='flex flex-col justify-center items-center' onSubmit={handleRegister}>
                                    <div className='text-gray-800 font-bold text-3xl mt-3 mb-3'>Sign up</div>
                                    <FontAwesomeIcon icon={faUserPlus} className='text-gray-800 text-4xl mt-2 mb-2' />
                                    <label htmlFor='email' className='mt-3'>
                                        Email:
                                    </label>
                                    <input
                                        type='email'
                                        id='email'
                                        autoComplete='email'
                                        required
                                        aria-invalid={validEmail ? "false" : "true"}
                                        onFocus={() => setEmailFocus(true)}
                                        onBlur={() => setEmailFocus(false)}
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        className='rounded-lg'
                                    />

                                    <label htmlFor='username' className='mt-3'>
                                        Username:
                                    </label>
                                    <input
                                        type='text'
                                        id='username'
                                        autoComplete='given-name'
                                        required
                                        aria-invalid={validUsername ? "false" : "true"}
                                        onFocus={() => setUsernameFocus(true)}
                                        onBlur={() => setUsernameFocus(false)}
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                        className='rounded-lg'
                                    />

                                    <label htmlFor='password' className='mt-3'>
                                        Password:
                                    </label>
                                    <input
                                        type='password'
                                        id='pwd'
                                        autoComplete='off'
                                        required
                                        aria-invalid={validPwd ? "false" : "true"}
                                        onFocus={() => setPwdFocus(true)}
                                        onBlur={() => setPwdFocus(false)}
                                        value={pwd}
                                        onChange={(e) => setPwd(e.target.value)}
                                        className='rounded-lg'
                                    />

                                    <label htmlFor='matchpwd' className='mt-3'>
                                        Confirm password:
                                    </label>
                                    <input
                                        type='password'
                                        id='matchpwd'
                                        autoComplete='off'
                                        required
                                        aria-invalid={validMatch ? "false" : "true"}
                                        onFocus={() => setMatchFocus(true)}
                                        onBlur={() => setMatchFocus(false)}
                                        value={matchPwd}
                                        onChange={(e) => setMatchPwd(e.target.value)}
                                        className='rounded-lg'
                                    />

                                    <Autocomplete
                                        isRequired
                                        label="Select currency"
                                        className="mt-4 w-1/3"
                                        size='md'
                                        allowsCustomValue={false}
                                        onSelectionChange={onCurrencyChange}
                                    >
                                        {currencies.map((currency) => (
                                            <AutocompleteItem key={currency.value} value={currency.value}>
                                                {currency.label}
                                            </AutocompleteItem>
                                        ))}
                                    </Autocomplete>


                                    <Button
                                        type='submit'
                                        variant='solid'
                                        className='mt-5 mb-5 cursor-pointer'
                                        disabled={!validEmail || !validUsername || !validPwd || !validMatch || isSubmitting}
                                    >
                                        Create an account
                                    </Button>

                                </form>
                            </section>
                        </div>

                    </>
                )}
                <div className='rounded-3xl border bg-gray-200 w-1/4 h-auto mt-8' style={{ boxShadow: "12px 12px 30px 5px rgba(62,66,66,0.35)" }}>
                    <div className='text-gray-800 text-base flex justify-center text-center font-semibold mt-2 mb-2'>
                        <Link color='foreground' href='/login'>
                            Click here to login
                        </Link>
                    </div>
                </div>

                <div className={errMsg ? 'flex flex-col justify-center items-center rounded-3xl border bg-red-300 w-1/4 h-auto mt-8' : 'hidden'}>
                    {errMsg && (
                        <div className='mt-2 mb-2 mr-2 ml-2 text-center'>
                            {errMsg}
                        </div>
                    )}
                </div>

                <div className={valMsg ? 'flex flex-col justify-center items-center rounded-3xl border bg-red-300 w-1/4 h-auto mt-8' : 'hidden'}>
                    {valMsg && (
                        <div className='mt-2 mb-2 mr-2 ml-2 text-center'>
                            {valMsg}
                        </div>
                    )}
                </div>
            </div>
        </>
    );
}

export default SignUp;