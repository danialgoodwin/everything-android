# Quick Android Examples





### ViewModel

    /** Example implementation of a ViewModel. The error handling could be better by providing a good message, rather than null. */
    class BusinessDetailsViewModel : ViewModel() {

        private val business = MutableLiveData<Business?>()

        fun getBusiness(): LiveData<Business?> = business

        fun updateBusiness(name: String, email: String) {
            Repo.getBusiness(name, email,
                onSuccess = { business.postValue(it) },
                onError = { business.postValue(null) }
            )
        }

    }

    // Usage
    val viewModel = ViewModelProviders.of(this).get(BusinessDetailsViewModel::class.java)
    viewModel.getBusiness().observe(this, Observer { business ->
        updateView(business)
    })




