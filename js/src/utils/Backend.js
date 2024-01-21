import axios from "axios";
import { compileUrl, handleApiResponse } from './Helpers'

const apiBaseUrlLocalHost = "http://localhost:8080/eo/v1";

const signupUserUrl = `${apiBaseUrlLocalHost}/user/signup`;
const loginUserUrl = `${apiBaseUrlLocalHost}/user/login`;

export async function signup(data) {
    try {
        const req = {
            email: data.email,
            username: data.username,
            password: data.pwd,
            userCurrency: data.currency,
        }

        console.log({
            url: signupUserUrl,
            request: req
        });

        const res = await axios.post(signupUserUrl, req);
        return handleApiResponse(res);
    } catch (e) {
        console.log(e.message);
        console.log(e.response);
        return null;
    }
}

export async function login(data) {
    try {
        const req = {
            email: data.email,
            password: data.password
        }

        console.log({
            url: loginUserUrl,
            request: req
        })

        const res = await axios.post(loginUserUrl, req);
        return handleApiResponse(res);
    } catch (e) {
        console.log(e.message);
        console.log(e.response);
        return null
    }
}