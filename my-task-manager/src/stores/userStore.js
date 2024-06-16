import { writable } from 'svelte/store';

function getSessionStorageItem(key, defaultValue) {
    if (typeof sessionStorage !== 'undefined') {
        return sessionStorage.getItem(key) || defaultValue;
    }
    return defaultValue;
}

const initialUser = {
    isLoggedIn: getSessionStorageItem('isLoggedIn', 'false') === 'true',
    name: getSessionStorageItem('userName', ''),
    phone: getSessionStorageItem('userPhone', ''),
    email: getSessionStorageItem('userEmail', '')
};

export const userStore = writable(initialUser);

if (typeof sessionStorage !== 'undefined') {
    userStore.subscribe((currentUser) => {
        sessionStorage.setItem('isLoggedIn', currentUser.isLoggedIn);
        sessionStorage.setItem('userName', currentUser.name);
        sessionStorage.setItem('userPhone', currentUser.phone);
        sessionStorage.setItem('userEmail', currentUser.email);
    });
}

export function setUser(userData) {
    userStore.set({
        isLoggedIn: true,
        name: userData.name,
        phone: userData.phone,
        email: userData.email
    });
}

export function clearUser() {
    userStore.set({
        isLoggedIn: false,
        name: '',
        phone: '',
        email: ''
    });
}
