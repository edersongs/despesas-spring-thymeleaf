alter table tdespesa change pago status varchar(20) not null;

alter table tdespesa change parcelasRestantes parcelasPagas int null default 0;

