<script>
    import ShieldLogo from '../assets/ShieldLogo.svg'
    import GSULogo from '../assets/georgia-state-university-logo-gsu.svg'

    // Component state
    let username = $state('');
    let password = $state('');

    // Handle login submission
    const handleLogin = () => {
        // Add your login logic here
        console.log('Login attempt:', { username, password });
    };
</script>

<!-- GSU Logo and Title Section -->
    <div class="text-center mb-6">
        <div class="flex justify-center mb-1">
            <img
                    src={GSULogo}
                    alt="Georgia State University"
                    class="h-[11vh] mb-1"
            />
        </div>
    </div>

    <!-- LIFESAFE Logo Section -->
    <div class="flex items-center justify-center mb-8 px-3">
        <img src={ShieldLogo} alt="Shield Logo" class="h-[5vh] pr-3">
        <div class="text-8xl tracking-wider">LIFESAFE</div>
    </div>

    <!-- Login Form -->
    <form on:submit|preventDefault={handleLogin}>
        <div class="mb-5">
            <label for="username" class="block text-white text-2xl mb-2">USERNAME</label>
            <input
                    type="text"
                    on:dragstart|preventDefault
                    on:drop|preventDefault
                    id="username"
                    bind:value={username}
                    class="w-full p-3 rounded-md bg-transparent bg-opacity-10 border border-white text-white focus:outline-none focus:ring-2 focus:ring-yellow-500 text-lg"
            />
        </div>

        <div class="mb-5">
            <label for="password" class="block text-white text-2xl mb-2">PASSWORD</label>
            <input
                    type="password"
                    on:dragstart|preventDefault
                    on:drop|preventDefault
                    id="password"
                    bind:value={password}
                    class="w-full p-3 rounded-md bg-transparent bg-opacity-10 border border-white text-white focus:outline-none focus:ring-2 focus:ring-yellow-500 text-lg"
            />
        </div>

        <div class="flex justify-between text-white text-sm mb-8">
            <a href="/forgot-password" class="text-lg hover:underline">FORGOT PASSWORD?</a>
            <a href="/create-account" class="text-lg hover:underline">CREATE ACCOUNT</a>
        </div>

        <button
                type="submit"
                class="w-full bg-jasper-orange font-bold tracking-wide py-3 rounded-full text-lg text-black transition duration-200 hover:border"
        >
            LOGIN
        </button>
    </form>

<style>
    @import "tailwindcss";

    @font-face {
        font-family: Madeleina-Sans;
        src: url("../assets/MadeleinaSans-2VY3.ttf")
    }

    @theme {
        --color-metallic-blue: #234A78;
        --color-jasper-orange: #D3941A;
    }

</style>