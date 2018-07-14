package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

class GetCompanyInitials {
    companion object {
        fun process (companyName : String) : String{
            var initials = ""
            if(companyName.isNotEmpty()) {
                val splitedString = companyName.split(" ")
                for (word in splitedString) {
                    initials += word.first()
                    if (initials.length == 3) break
                }
            }
            return  initials
        }
    }
}