<script>
    import { onMount } from 'svelte';
    import welcome from '$lib/images/welcome.png';
    import SignUp from '../components/SignUp.svelte';
    import Login from '../components/Login.svelte';
    import Modal from '../components/Modal.svelte';
    import { userStore } from '../stores/userStore';
    import { get } from 'svelte/store';

    let showSignUp = false;
    let showLogin = false;
    let user = {};

    $: user = get(userStore);

    onMount(() => {
        user = get(userStore);  // Initialize user from store
    });

    function openSignUp() {
        showSignUp = true;
    }

    function closeSignUp() {
        showSignUp = false;
    }

    function openLogin() {
        showLogin = true;
    }

    function closeLogin() {
        showLogin = false;
    }
</script>

<svelte:head>
    <title>Home</title>
    <meta name="description" content="Task Manager app" />
</svelte:head>

<section>
    <h1>
        <span class="welcome">
            <picture>
                <img src={welcome} alt="TaskManager" />
            </picture>
        </span>
        Simplify Tasks, Amplify Results
    </h1>
    {#if !user.isLoggedIn}
    <div class="buttons">
        <button on:click={openSignUp} class="button">Sign Up</button>
        <button on:click={openLogin} class="button">Login</button>
    </div>
    {:else}
    <h1>Welcome, {user.name}</h1>
    {/if}
    <h2>Get your own To-Do list Task Manager</h2>
</section>

{#if showSignUp}
    <Modal on:close={closeSignUp}>
        <SignUp />
    </Modal>
{/if}

{#if showLogin}
    <Modal on:close={closeLogin}>
        <Login />
    </Modal>
{/if}

<style>
    section {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        text-align: center;
        padding: 20px;
    }

    h1 {
        width: 100%;
        margin-bottom: 20px;
        font-size: 2rem;
    }

    .welcome {
        display: block;
        position: relative;
        width: 100%;
        height: 0;
        padding-bottom: calc(100% * 495 / 2048);
        margin-bottom: 20px;
    }

    .welcome img {
        position: absolute;
        width: 100%;
        height: 100%;
        top: 0;
        display: block;
    }

    .buttons {
        margin-top: 20px;
        display: flex;
        justify-content: center;
        gap: 10px;
    }

    .button {
        display: inline-block;
        padding: 10px 20px;
        font-size: 16px;
        color: #fff;
        background-color: #10b798;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s ease;
    }

    .button:hover {
        background-color: #096453;
    }

    h2 {
        margin-top: 30px;
        font-size: 1.5rem;
        color: #555;
    }
</style>
