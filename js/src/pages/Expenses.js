import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ensureAuthenticated } from '../utils/Helpers';
import { EoUser } from '../utils/EoUser';


function Expenses() {
    const userID = EoUser.id;

    const navigate = useNavigate();

    useEffect(() => {
        ensureAuthenticated(navigate);
    }, []);

    return (
        <>
        </>
    );
}

export default Expenses;