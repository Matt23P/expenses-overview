import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { ensureAuthenticated } from '../utils/Helpers';

function MyAccount() {

    const navigate = useNavigate();

    useEffect(() => {
        ensureAuthenticated(navigate);
    }, []);

    return (
        <>
        </>
    );
}

export default MyAccount;