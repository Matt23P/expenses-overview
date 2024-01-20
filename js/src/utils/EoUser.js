class AppUser {
    static #USER_CACHE = "APP_USER";
    static #SLIDING_EXPIRATION_TIME = 1000 * 60 * 60 * 12;

    #id;
    #username;
    #email;
    #creationDate;
    #userCurrency;

    constructor(id, username, email, creationDate, userCurrency) {
        this.#id = id ?? null;
        this.#username = username ?? null;
        this.#email = email ?? null;
        this.#creationDate = creationDate ?? null;
        this.#userCurrency = userCurrency ?? null;
    }

    get id() { return this.#id }
    get username() { return this.#username }
    get email() { return this.#email }
    get creationDate() { return this.#creationDate }
    get userCurrency() { return this.#userCurrency }
    get isLogged() { return !!this.#id }

    update(updatedUser) {
        this.#id = updatedUser.id;
        this.#username = updatedUser.username;
        this.#email = updatedUser.emaill;
        this.#creationDate = updatedUser.creationDate;
        this.#userCurrency = updatedUser.userCurrency;

        AppUser.#saveToCache(this);
    }

    logout() {
        this.#id = null;
        this.#email = null;
        this.#username = null;
        this.#creationDate = null;
        this.#userCurrency = null;

        AppUser.#removeFromCache();
    }

    static #removeFromCache() {
        localStorage.removeItem(AppUser.#USER_CACHE);
    }

    static #getFromCache() {
        const now = new Date();

        const cachedUserJson = localStorage.getItem(AppUser.#USER_CACHE)
        const cachedUser = JSON.parse(cachedUserJson);

        if (!cachedUser || cachedUser.expireTime < now.getTime()) {
            AppUser.#removeFromCache();
            return null;
        }

        return cachedUser;
    }

    static #saveToCache(user) {
        const now = new Date();

        const cachedUser = {
            id: user.id,
            email: user.email,
            username: user.username,
            creationDate: user.creationDate,
            userCurrency: user.userCurrency,
            expireTime: now.getTime() + AppUser.#SLIDING_EXPIRATION_TIME,
        }

        const cachedUserJson = JSON.stringify(cachedUser);
        localStorage.setItem(AppUser.#USER_CACHE, cachedUserJson)
    }

    static get() {
        const cachedUser = AppUser.#getFromCache();
        return !!cachedUser
            ? new AppUser(cachedUser.id, cachedUser.email, cachedUser.username, cachedUser.creationDate, cachedUser.userCurrency)
            : new AppUser();
    }
}

export const EoUser = AppUser.get();