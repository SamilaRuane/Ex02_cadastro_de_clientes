package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.common

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.EntrepreneurData
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur

object Mapper {
    fun toEntrepreneur(entrepreneurData: EntrepreneurData): Entrepreneur = Entrepreneur(fullName = entrepreneurData.fullName,
            email = entrepreneurData.email,
            phone = entrepreneurData.phone,
            tradeName = entrepreneurData.tradeName,
            birthDate = entrepreneurData.birthDate.toDate(),
            individualEntrepreneur = entrepreneurData.individualEntrepreneur)

    fun toEntrepreneurData(entrepreneur: Entrepreneur): EntrepreneurData = EntrepreneurData(id = 0, fullName = entrepreneur.fullName,
            email = entrepreneur.email,
            phone = entrepreneur.phone,
            tradeName = entrepreneur.tradeName,
            birthDate = entrepreneur.birthDate.time,
            individualEntrepreneur = entrepreneur.individualEntrepreneur)
}