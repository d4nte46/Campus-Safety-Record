<!-- CreateAccount.svelte -->
<script>
    import ShieldLogo from '../assets/ShieldLogo.svg'


    // Reactive state using Svelte 5 runes
    let formData = $state({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: ''
    });

    let isSubmitting = $state(false);
    let submitStatus = $state({
        success: false,
        error: false,
        message: ''
    });

    // Form validation
    let formErrors = $state({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: ''
    });

    let formValid = $state(true);

    function validateForm() {
        // Reset errors
        formErrors = {
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            confirmPassword: ''
        };

        let isValid = true;

        // Validate first name
        if (!formData.firstName.trim()) {
            formErrors.firstName = 'First name is required';
            isValid = false;
        }

        // Validate last name
        if (!formData.lastName.trim()) {
            formErrors.lastName = 'Last name is required';
            isValid = false;
        }

        // Validate email
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!formData.email) {
            formErrors.email = 'Email is required';
            isValid = false;
        } else if (!emailRegex.test(formData.email)) {
            formErrors.email = 'Please enter a valid email address';
            isValid = false;
        }

        // Validate password
        if (!formData.password) {
            formErrors.password = 'Password is required';
            isValid = false;
        } else if (formData.password.length < 8) {
            formErrors.password = 'Password must be at least 8 characters';
            isValid = false;
        }

        // Validate password confirmation
        if (!formData.confirmPassword) {
            formErrors.confirmPassword = 'Please confirm your password';
            isValid = false;
        } else if (formData.confirmPassword !== formData.password) {
            formErrors.confirmPassword = 'Passwords do not match';
            isValid = false;
        }

        return isValid;
    }

    $effect(() => {
        formValid = Object.values(formErrors).every(error => !error);
    });

    function validateField(field) {
        const validators = {
            firstName: () => {
                if (!formData.firstName.trim()) {
                    formErrors.firstName = 'First name is required';
                    return false;
                }
                formErrors.firstName = '';
                return true;
            },
            lastName: () => {
                if (!formData.lastName.trim()) {
                    formErrors.lastName = 'Last name is required';
                    return false;
                }
                formErrors.lastName = '';
                return true;
            },
            email: () => {
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!formData.email) {
                    formErrors.email = 'Email is required';
                    return false;
                } else if (!emailRegex.test(formData.email)) {
                    formErrors.email = 'Please enter a valid email address';
                    return false;
                }
                formErrors.email = '';
                return true;
            },
            password: () => {
                if (!formData.password) {
                    formErrors.password = 'Password is required';
                    return false;
                } else if (formData.password.length < 8) {
                    formErrors.password = 'Password must be at least 8 characters';
                    return false;
                }
                formErrors.password = '';
                return true;
            },
            confirmPassword: () => {
                if (!formData.confirmPassword) {
                    formErrors.confirmPassword = 'Please confirm your password';
                    return false;
                } else if (formData.confirmPassword !== formData.password) {
                    formErrors.confirmPassword = 'Passwords do not match';
                    return false;
                }
                formErrors.confirmPassword = '';
                return true;
            }
        };

        if (validators[field]) {
            return validators[field]();
        }
        return true;
    }

    async function handleSubmit() {
        // Validate form
        const isValid = validateForm();

        if (!isValid) {
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
                message: 'Account created successfully! You can now log in.'
            };

            // Clear form
            formData = {
                firstName: '',
                lastName: '',
                email: '',
                password: '',
                confirmPassword: ''
            };
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

<div class="">
    <div class="sm:mx-auto sm:w-full sm:max-w-md">
        <img class="mx-auto h-12 w-auto" src={ShieldLogo} alt="Company Logo">
        <h2 class="mt-6 text-center text-3xl font-bold tracking-tight">Create your account</h2>
        <p class="mt-2 text-center text-sm ">
            Already have an account?
            <a href="/" class="font-medium text-blue-600 hover:text-blue-500">
                Sign in
            </a>
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
                            <p class="text-sm font-medium text-green-800">{submitStatus.message}</p>
                        </div>
                    </div>
                </div>

                <div class="mt-6">
                    <div class="text-center">
                        <a href="/" class="font-medium text-blue-600 hover:text-blue-500">
                            Go to login
                        </a>
                    </div>
                </div>
            {:else}
                <form class="space-y-6" on:submit|preventDefault={handleSubmit}>
                    <!-- First Name Field -->
                    <div>
                        <label for="firstName" class="block text-sm font-medium text-gray-700">
                            First Name
                        </label>
                        <div class="mt-1">
                            <input
                                    id="firstName"
                                    type="text"
                                    autocomplete="given-name"
                                    required
                                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm {formErrors.firstName ? 'border-red-300 text-red-900 placeholder-red-300 focus:border-red-500 focus:ring-red-500' : ''}"
                                    bind:value={formData.firstName}
                                    on:blur={() => validateField('firstName')}
                            />
                        </div>
                        {#if formErrors.firstName}
                            <p class="mt-2 text-sm text-red-600">{formErrors.firstName}</p>
                        {/if}
                    </div>

                    <!-- Last Name Field -->
                    <div>
                        <label for="lastName" class="block text-sm font-medium text-gray-700">
                            Last Name
                        </label>
                        <div class="mt-1">
                            <input
                                    id="lastName"
                                    type="text"
                                    autocomplete="family-name"
                                    required
                                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm {formErrors.lastName ? 'border-red-300 text-red-900 placeholder-red-300 focus:border-red-500 focus:ring-red-500' : ''}"
                                    bind:value={formData.lastName}
                                    on:blur={() => validateField('lastName')}
                            />
                        </div>
                        {#if formErrors.lastName}
                            <p class="mt-2 text-sm text-red-600">{formErrors.lastName}</p>
                        {/if}
                    </div>

                    <!-- Email Field -->
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
                                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm {formErrors.email ? 'border-red-300 text-red-900 placeholder-red-300 focus:border-red-500 focus:ring-red-500' : ''}"
                                    bind:value={formData.email}
                                    on:blur={() => validateField('email')}
                            />
                        </div>
                        {#if formErrors.email}
                            <p class="mt-2 text-sm text-red-600">{formErrors.email}</p>
                        {/if}
                    </div>

                    <!-- Password Field -->
                    <div>
                        <label for="password" class="block text-sm font-medium text-gray-700">
                            Password
                        </label>
                        <div class="mt-1">
                            <input
                                    id="password"
                                    type="password"
                                    autocomplete="new-password"
                                    required
                                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm {formErrors.password ? 'border-red-300 text-red-900 placeholder-red-300 focus:border-red-500 focus:ring-red-500' : ''}"
                                    bind:value={formData.password}
                                    on:blur={() => validateField('password')}
                            />
                        </div>
                        {#if formErrors.password}
                            <p class="mt-2 text-sm text-red-600">{formErrors.password}</p>
                        {/if}
                    </div>

                    <!-- Confirm Password Field -->
                    <div>
                        <label for="confirmPassword" class="block text-sm font-medium text-gray-700">
                            Confirm Password
                        </label>
                        <div class="mt-1">
                            <input
                                    id="confirmPassword"
                                    type="password"
                                    autocomplete="new-password"
                                    required
                                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 sm:text-sm {formErrors.confirmPassword ? 'border-red-300 text-red-900 placeholder-red-300 focus:border-red-500 focus:ring-red-500' : ''}"
                                    bind:value={formData.confirmPassword}
                                    on:blur={() => validateField('confirmPassword')}
                            />
                        </div>
                        {#if formErrors.confirmPassword}
                            <p class="mt-2 text-sm text-red-600">{formErrors.confirmPassword}</p>
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
                                Creating Account...
                            {:else}
                                Create Account
                            {/if}
                        </button>
                    </div>
                </form>
            {/if}
        </div>
    </div>
</div>