<!-- ForgotPassword.svelte -->
<script>
    import ShieldLogo from '../assets/ShieldLogo.svg'

    let email = $state('');
    let isSubmitting = $state(false);
    let submitStatus = $state({
        success: false,
        error: false,
        message: ''
    });

    // Form validation
    let isEmailValid = $state(true);
    let formErrors = $state({
        email: ''
    });

    function validateEmail(value) {
        if (!value) {
            formErrors.email = 'Email is required';
            return false;
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(value)) {
            formErrors.email = 'Please enter a valid email address';
            return false;
        }

        formErrors.email = '';
        return true;
    }

    $effect(() => {
        isEmailValid = !formErrors.email;
    });

    async function handleSubmit() {
        // Validate form
        isEmailValid = validateEmail(email);

        if (!isEmailValid) {
            return;
        }

        // Simulate API call
        isSubmitting = true;
        submitStatus = { success: false, error: false, message: '' };

        try {
            // Simulate network delay
            await new Promise(resolve => setTimeout(resolve, 1500));

            // Dummy success response
            submitStatus = {
                success: true,
                error: false,
                message: 'Password reset link sent! Please check your email.'
            };

            // Clear form
            email = '';
        } catch (error) {
            // Dummy error handling
            submitStatus = {
                success: false,
                error: true,
                message: 'Something went wrong. Please try again later.'
            };
        } finally {
            isSubmitting = false;
        }
    }
</script>

 <div class="sm:mx-auto sm:w-full sm:max-w-md">
        <img class="mx-auto h-12 w-auto" src={ShieldLogo} alt="Company Logo">
        <h2 class="mt-6 text-center text-3xl font-bold tracking-tight">Reset your password</h2>
        <p class="mt-2 text-center text-m ">
            Enter your email address and we'll send you a link to reset your password.
        </p>
    </div>

    <div class="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div class="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
            {#if submitStatus.success}
                <div class="rounded-md bg-green-50 p-4 mb-6">
                    <div class="flex">
                        <div class="flex-shrink-0">
                            <!-- Success Icon -->
                            <svg class="h-5 w-5 text-green-400" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.857-9.809a.75.75 0 00-1.214-.882l-3.483 4.79-1.88-1.88a.75.75 0 10-1.06 1.061l2.5 2.5a.75.75 0 001.137-.089l4-5.5z" clip-rule="evenodd" />
                            </svg>
                        </div>
                        <div class="ml-3">
                            <p class="text-md font-medium text-green-800">{submitStatus.message}</p>
                        </div>
                    </div>
                </div>

                <div class="mt-6">
                    <div class="text-center">
                        <a href="/" class="font-medium text-blue-600 hover:text-blue-500">
                            Return to login
                        </a>
                    </div>
                </div>
            {:else}
                <form class="space-y-6" on:submit|preventDefault={handleSubmit}>
                    <div>
                        <label for="email" class="block text-sm font-medium text-gray-700">
                            Email address
                        </label>
                        <div class="mt-1">
                            <input
                                    id="email"
                                    type="email"
                                    autocomplete="email"
                                    required
                                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-md text-black {!isEmailValid ? 'border-red-300 text-red-900 placeholder-red-300 focus:border-red-500 focus:ring-red-500' : ''}"
                                    bind:value={email}
                                    on:blur={() => validateEmail(email)}
                            />
                        </div>
                        {#if formErrors.email}
                            <p class="mt-2 text-sm text-red-600">{formErrors.email}</p>
                        {/if}
                    </div>

                    {#if submitStatus.error}
                        <div class="rounded-md bg-red-50 p-4">
                            <div class="flex">
                                <div class="flex-shrink-0">
                                    <!-- Error Icon -->
                                    <svg class="h-5 w-5 text-red-400" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                                        <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.28 7.22a.75.75 0 00-1.06 1.06L8.94 10l-1.72 1.72a.75.75 0 101.06 1.06L10 11.06l1.72 1.72a.75.75 0 101.06-1.06L11.06 10l1.72-1.72a.75.75 0 00-1.06-1.06L10 8.94 8.28 7.22z" clip-rule="evenodd" />
                                    </svg>
                                </div>
                                <div class="ml-3">
                                    <p class="text-sm font-medium text-red-800">{submitStatus.message}</p>
                                </div>
                            </div>
                        </div>
                    {/if}

                    <div>
                        <button
                                type="submit"
                                class="flex w-full justify-center rounded-md bg-blue-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-blue-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600"
                                disabled={isSubmitting}
                        >
                            {#if isSubmitting}
                                <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                                </svg>
                                Processing...
                            {:else}
                                Send reset link
                            {/if}
                        </button>
                    </div>
                </form>

                <div class="mt-6">
                    <div class="relative">
                        <div class="absolute inset-0 flex items-center">
                            <div class="w-full border-t border-gray-300"></div>
                        </div>
                        <div class="relative flex justify-center text-sm">
                            <span class="bg-white px-2 text-gray-500">Or</span>
                        </div>
                    </div>

                    <div class="mt-6 text-center">
                        <a href="/" class="font-medium text-blue-600 hover:text-blue-500">
                            Return to login
                        </a>
                    </div>
                </div>
            {/if}
        </div>
    </div>