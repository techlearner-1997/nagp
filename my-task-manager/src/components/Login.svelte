<script>
    import { setUser } from '../stores/userStore';

    let signinData = {
        email: '',
        password: ''
    };

    async function login() {
        try {
            const response = await fetch(`http://localhost:8080/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(signinData)
            });

            if (response.ok) {
                const data = await response.json();
                const jwtToken = data.jwtToken;
                sessionStorage.setItem('jwtToken', jwtToken);
                sessionStorage.setItem('userId', data.email);

                const getresponse = await fetch(`http://localhost:8080/users/getUser?email=${data.email}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${jwtToken}`
                    }
                });

                if (getresponse.ok) {
                    const userData = await getresponse.json();
                    console.log('User data fetched successfully:', userData);
                    setUser(userData); // Update the user store
                    window.location.href = '/';
                } else {
                    console.error('Fetch failed with status:', getresponse.status);
                    alert('Fetching user data failed');
                }
            } else {
                console.error('Login failed with status:', response.status);
                alert('SignIn Failed');
            }
        } catch (error) {
            console.error('Error during login:', error);
            alert('SignIn Failed');
        }
    }
</script>

<form on:submit|preventDefault={login}>
    <h2>Login</h2>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" bind:value={signinData.email} />
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" bind:value={signinData.password} />
    </div>
    <button type="submit">Login</button>
</form>

<style>
    form {
        display: flex;
        flex-direction: column;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        margin-bottom: 5px;
    }

    .form-group input {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
    }

    button {
        padding: 10px;
        background-color: #10b798;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:hover {
        background-color: #096453;
    }
</style>
