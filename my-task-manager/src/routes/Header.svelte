<script>
    import { page } from '$app/stores';
    import { onMount } from 'svelte';
    import { writable } from 'svelte/store';
    import logo from '$lib/images/logo.png';
    import user from '$lib/images/user.png';
	import {clearUser } from '../stores/userStore';

    const sessionUser = writable('');
    const showDropdown = writable(false);

    onMount(() => {
        const userId = sessionStorage.getItem('userId');
        if (userId) {
            sessionUser.set(userId);
        }
    });

    function logout() {
        sessionStorage.removeItem('userId');
        sessionUser.set('');
		clearUser();
        showDropdown.set(false);
    }

    function handleUserIconClick() {
        if ($sessionUser) {
            showDropdown.update(value => !value);
        } else {
            window.location.href = '/';
        }
    }
</script>

<header>
    <div class="corner">
        <a href="./">
            <img src={logo} alt="TaskManager" />
        </a>
    </div>

    <nav>
        <svg viewBox="0 0 2 3" aria-hidden="true">
            <path d="M0,0 L1,2 C1.5,3 1.5,3 2,3 L2,0 Z" />
        </svg>
        <ul>
            <li aria-current={$page.url.pathname === '/' ? 'page' : undefined}>
                <a href="/">Home</a>
            </li>
            <li aria-current={$page.url.pathname === '/about' ? 'page' : undefined}>
                <a href="/about">About</a>
            </li>
            {#if $sessionUser}
            <li aria-current={$page.url.pathname.startsWith('/tasks') ? 'page' : undefined}>
                <a href="/tasks">Task Manager</a>
            </li>
            {/if}
        </ul>
        <svg viewBox="0 0 2 3" aria-hidden="true">
            <path d="M0,0 L0,3 C0.5,3 0.5,3 1,2 L2,0 Z" />
        </svg>
    </nav>

    <div class="corner user-icon" on:click={handleUserIconClick}>
        <img src={user} alt="User" />
        {#if $showDropdown}
        <div class="dropdown">
            <button on:click={logout}>Logout</button>
        </div>
        {/if}
    </div>
</header>

<style>
    header {
        display: flex;
        justify-content: space-between;
    }

    .corner {
        width: 8em;
        height: 4em;
    }

    .corner img {
        width: 8em;
        height: 4em;
        object-fit: contain;
        cursor: pointer;
    }

    nav {
        display: flex;
        justify-content: center;
        --background: rgba(255, 255, 255, 0.7);
    }

    svg {
        width: 2em;
        height: 3em;
        display: block;
    }

    path {
        fill: var(--background);
    }

    ul {
        position: relative;
        padding: 0;
        margin: 0;
        height: 3em;
        display: flex;
        justify-content: center;
        align-items: center;
        list-style: none;
        background: var(--background);
        background-size: contain;
    }

    li {
        position: relative;
        height: 100%;
    }

    li[aria-current='page']::before {
        --size: 6px;
        content: '';
        width: 0;
        height: 0;
        position: absolute;
        top: 0;
        left: calc(50% - var(--size));
        border: var(--size) solid transparent;
        border-top: var(--size) solid var(--color-theme-1);
    }

    nav a {
        display: flex;
        height: 100%;
        align-items: center;
        padding: 0 0.5rem;
        color: var(--color-text);
        font-weight: 700;
        font-size: 0.8rem;
        text-transform: uppercase;
        letter-spacing: 0.1em;
        text-decoration: none;
        transition: color 0.2s linear;
    }

    a:hover {
        color: var(--color-theme-1);
    }

    .user-icon {
        position: relative;
    }

    .dropdown {
        position: absolute;
        top: 100%;
        right: 0;
        background-color: white;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        z-index: 1;
    }

    .dropdown button {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #10b798;
        color: white;
        border: none;
        cursor: pointer;
        text-align: left;
    }

    .dropdown button:hover {
        background-color: #096453;
    }
</style>
