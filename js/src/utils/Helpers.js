import { EoUser } from "./EoUser";

export function ensureAuthenticated(navigate) {
    if (!EoUser.isLogged) {
        navigate("/login");
    }
}

export function ensureNotAuthenticated(navigate) {
    if (EoUser.isLogged) {
        navigate("/");
    }
}

export default function refreshPage() {
    window.location.reload();
}

export function dateToDateOnlyString(date) {
    if (date == null)
        return null;

    date = new Date(date);
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();

    return `${pad(year, 4)}-${pad(month, 2)}-${pad(day, 2)}`;
}

export function dateToTimeOnlyString(date) {
    if (date == null)
        return null;

    date = new Date(date);
    const hour = date.getHours();
    const minute = date.getMinutes();

    return `${pad(hour, 2)}:${pad(minute, 2)}`;
}

export function dateToString(date) {
    if (date == null)
        return null;

    const dateOnly = dateToDateOnlyString(date);
    const timeOnly = dateToTimeOnlyString(date);

    return `${dateOnly} ${timeOnly}`;
}

function pad(obj, count = 2, char = '0', start = true) {
    const str = String(obj);
    return !!start
        ? str.padStart(count, char)
        : str.padEnd(count, char);
}

export function compileUrl(url, params) {
    url = String(url);
    for (let [key, value] of Object.entries(params)) {
        url = url.replace(`{${key}}`, String(value));
    }
    return url;
}

export function handleApiResponse(res) {
    const status = res.status;
    if (status === 404) {
        console.log("Resources not found!")
        return null;
    }

    return res.data;
}