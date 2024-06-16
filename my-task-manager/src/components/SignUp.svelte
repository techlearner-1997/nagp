<script lang=ts>
    import { createEventDispatcher } from 'svelte';
    const dispatch = createEventDispatcher();
    let signupData = {
        name: '',
        password: '',
        phone: '',
        email: ''
    };

    async function signUp() {
        try {
            const response = await fetch(`http://localhost:8080/auth/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(signupData)
            });

            if (response.ok) {
                alert('SignUp Successful.Please Login');
                // You can add any additional logic here, such as redirecting the user
            } else {
                const errorData = await response.json();
                dispatch('close');
                alert(`SignUp Failed: ${errorData.message}`);
                
            }
        } catch (error:any) {
            alert(`SignUp Failed: ${error.message}`);
        }
    }
</script>

<form on:submit|preventDefault={signUp}>
    <h2>Sign Up</h2>
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" bind:value={signupData.name} />
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" bind:value={signupData.email} />
    </div>
    <div class="form-group">
        <label for="phone">Mobile</label>
        <input type="tel" id="phone" bind:value={signupData.phone} />
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" bind:value={signupData.password} />
    </div>
    <button type="submit">Sign Up</button>
</form>

<style>
    form {
        display: flex;
        flex-direction: column;
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 8px;
        background-color: #f9f9f9;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .form-group input {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button {
        padding: 10px;
        background-color: #10b798;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #096453;
    }
</style>
